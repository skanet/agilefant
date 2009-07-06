package fi.hut.soberit.agilefant.web;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.opensymphony.xwork2.Action;

import fi.hut.soberit.agilefant.business.PersonalLoadBusiness;
import fi.hut.soberit.agilefant.business.UserBusiness;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.transfer.IntervalLoadContainer;

public class UserLoadActionTest {
    private UserLoadAction userLoadAction;
    private UserBusiness userBusiness;
    private PersonalLoadBusiness personalLoadBusiness;
    
    @Before
    public void setUp() {
        this.userLoadAction = new UserLoadAction();
        this.userBusiness = createStrictMock(UserBusiness.class);
        this.personalLoadBusiness = createStrictMock(PersonalLoadBusiness.class);
        userLoadAction.setPersonalLoadBusiness(personalLoadBusiness);
        userLoadAction.setUserBusiness(userBusiness);
    }
    private void replayAll() {
        replay(userBusiness, personalLoadBusiness);
    }
    private void verifyAll() {
        verify(userBusiness, personalLoadBusiness);
    }
    
    @Test
    public void testRetrieveUserLoad() {
        User user = new User();
        List<IntervalLoadContainer> loadData = Collections.emptyList();
        expect(userBusiness.retrieve(1)).andReturn(user);
        expect(personalLoadBusiness.retrieveUserLoad(user, UserLoadAction.DEFAULT_LOAD_INTERVAL_LENGTH)).andReturn(loadData);
        userLoadAction.setUserId(1);
        replayAll();
        assertEquals(Action.SUCCESS, userLoadAction.retrieveUserLoad());
        assertNotNull(userLoadAction.getUserLoadData());
        verifyAll();
    }
}

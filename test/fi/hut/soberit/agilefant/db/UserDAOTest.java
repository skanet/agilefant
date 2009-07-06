package fi.hut.soberit.agilefant.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.test.AbstractHibernateTests;

@ContextConfiguration
@Transactional
public class UserDAOTest extends AbstractHibernateTests {
    
    @Autowired
    private UserDAO userDAO;
    
    @Test
    public void testGetByLoginName() {
        executeClassSql();
        assertEquals(4, userDAO.getByLoginName("user4").getId());
    }
    
    @Test
    public void testGetByLoginName_notFound() {
        assertNull(userDAO.getByLoginName("usernotfound"));
    }

    @Test
    public void testListUsersByEnabledStatus_enabled() {
        executeClassSql();
        List<User> users = userDAO.listUsersByEnabledStatus(true);
        assertEquals(3, users.size());
        List<Integer> foundIds = new ArrayList<Integer>();
        for (User user : users) {
            foundIds.add(user.getId());
        }
        assertTrue(foundIds.contains(1));
        assertTrue(foundIds.contains(2));
        assertTrue(foundIds.contains(4));
    }

    @Test
    public void testListUsersByEnabledStatus_nousers() {
        List<User> users = userDAO.listUsersByEnabledStatus(true);
        assertEquals(0, users.size());
    }

    @Test
    public void testListUsersByEnabledStatus_disabled() {
        executeClassSql();
        List<User> users = userDAO.listUsersByEnabledStatus(false);
        assertEquals(1, users.size());
        List<Integer> foundIds = new ArrayList<Integer>();
        for (User user : users) {
            foundIds.add(user.getId());
        }
        assertTrue(foundIds.contains(3));
    }
    
}

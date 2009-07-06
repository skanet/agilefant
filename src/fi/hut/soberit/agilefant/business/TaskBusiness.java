package fi.hut.soberit.agilefant.business;

import java.util.Collection;
import java.util.Set;

import fi.hut.soberit.agilefant.exception.ObjectNotFoundException;
import fi.hut.soberit.agilefant.model.Task;
import fi.hut.soberit.agilefant.util.ResponsibleContainer;

public interface TaskBusiness extends GenericBusiness<Task> {

    /**
     * Populates and stores a task.
     * @param storyId the parent story's id, or zero if none.
     * @return the newly stored task
     */
    public Task storeTask(Task task, Integer iterationId, Integer storyId, Set<Integer> userIds);
    
    public Collection<ResponsibleContainer> getTaskResponsibles(Task task);

    public Task resetOriginalEstimate(int taskId);

    /**
     * Moves the tasks and updates both new and old iteration histories.
     */
    public Task move(Task task, Integer iterationId, Integer storyId);
    
    /**
     * Sets the tasks parent.
     * <p>
     * Parent can be either a story or an iteration. Only one of
     * the parameters: <code>storyId</code> or <code>iterationId</code>
     * should be given.
     * @throws IllegalArgumentException if both ids or none were given
     * @throws ObjectNotFoundException if iteration or story was not found
     */
    public void assignParentForTask(Task task, Integer iterationId, Integer storyId)
        throws IllegalArgumentException, ObjectNotFoundException;
}

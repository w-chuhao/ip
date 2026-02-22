package chu.tasks;

/**
 * Represents a generic task with a description and completion state.
 */
public class Tasks {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description Task description.
     */
    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {

        this.isDone = false;
    }

    /**
     * Returns the status icon used for display.
     *
     * @return {@code X} if done, otherwise a blank space.
     */
    public String getStatusIcon() {

        return isDone ? "X" : " ";
    }

    /**
     * Returns the task description.
     *
     * @return Task description text.
     */
    public String getDescription() {

        return description;
    }

    /**
     * Returns a formatted display string for this task.
     *
     * @return Task string including status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

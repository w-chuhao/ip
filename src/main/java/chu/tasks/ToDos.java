package chu.tasks;

/**
 * Represents a to-do task.
 */
public class ToDos extends Tasks {

    /**
     * Creates a to-do task with the given description.
     *
     * @param description To-do description.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a formatted display string for a to-do task.
     *
     * @return String representation prefixed with task type.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

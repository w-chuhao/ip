package chu.tasks;

import chu.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents a deadline task with a due date and time.
 */
public class Deadlines extends Tasks {
    protected LocalDateTime by;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Creates a deadline task.
     *
     * @param description Deadline description.
     * @param by Due date-time.
     */
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date-time of this deadline.
     *
     * @return Due date-time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a formatted display string for a deadline task.
     *
     * @return String representation prefixed with task type and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeParser.format(by) + ")";
    }
}

package chu.tasks;

import chu.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents an event task with start and end date-times.
 */
public class Events extends Tasks {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Creates an event task.
     *
     * @param description Event description.
     * @param start Event start date-time.
     * @param end Event end date-time.
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the event start date-time.
     *
     * @return Event start date-time.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns the event end date-time.
     *
     * @return Event end date-time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns a formatted display string for an event task.
     *
     * @return String representation with event start and end date-times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + dateTimeParser.format(start)
                + " to: " + dateTimeParser.format(end) + ")";
    }
}

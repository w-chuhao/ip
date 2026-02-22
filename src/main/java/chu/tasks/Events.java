package chu.tasks;

import chu.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Events extends Tasks {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + dateTimeParser.format(start)
                + " to: " + dateTimeParser.format(end) + ")";
    }
}

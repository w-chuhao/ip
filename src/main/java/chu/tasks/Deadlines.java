package chu.tasks;

import chu.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadlines extends Tasks {
    protected LocalDateTime by;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeParser.format(by) + ")";
    }
}

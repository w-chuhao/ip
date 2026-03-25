package chu.tasks;

import chu.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    private final DateTimeParser dateTimeParser = new DateTimeParser();

    public Deadline(String description, LocalDateTime by) {
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

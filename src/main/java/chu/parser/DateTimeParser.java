package chu.parser;

import chu.errorhandler.ChuException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private final DateTimeFormatter input = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public LocalDateTime parseInput(String raw) throws ChuException {
        try {
            return LocalDateTime.parse(raw.trim(), input);
        } catch (DateTimeParseException e) {
            throw new ChuException("Use date/time format d/M/yyyy HHmm, e.g. 2/12/2019 1800");
        }
    }

    public String format(LocalDateTime dt) {
        return dt.format(output);
    }
}

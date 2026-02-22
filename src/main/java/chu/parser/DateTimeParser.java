package chu.parser;

import chu.errorHandler.ChuExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private DateTimeFormatter input = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public LocalDateTime parseInput(String raw) throws ChuExceptions {
        try {
            return LocalDateTime.parse(raw.trim(), input);
        } catch (DateTimeParseException e) {
            throw new ChuExceptions("Use date/time format d/M/yyyy HHmm, e.g. 2/12/2019 1800");
        }
    }

    public String format(LocalDateTime dt) {
        return dt.format(output);
    }
}

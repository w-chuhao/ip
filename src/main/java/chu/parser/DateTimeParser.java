package chu.parser;

import chu.errorHandler.ChuExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts date-time strings between user-facing and internal formats.
 */
public class DateTimeParser {
    private DateTimeFormatter input = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Parses user input into a {@code LocalDateTime}.
     *
     * @param raw Date-time text in {@code d/M/yyyy HHmm} format.
     * @return Parsed date-time.
     * @throws ChuExceptions If the input does not match the expected format.
     */
    public LocalDateTime parseInput(String raw) throws ChuExceptions {
        try {
            return LocalDateTime.parse(raw.trim(), input);
        } catch (DateTimeParseException e) {
            throw new ChuExceptions("Use date/time format d/M/yyyy HHmm, e.g. 2/12/2019 1800");
        }
    }

    /**
     * Formats a date-time value for display.
     *
     * @param dt Date-time to format.
     * @return Human-readable formatted date-time string.
     */
    public String format(LocalDateTime dt) {
        return dt.format(output);
    }
}

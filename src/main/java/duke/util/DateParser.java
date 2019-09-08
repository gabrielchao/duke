package duke.util;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.List;

/**
 * Utility class with various date and datetime parsing methods.
 */
public class DateParser {
    private static List<DateTimeFormatter> dateFormatters = List.of(
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL),
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT),
            DateTimeFormatter.ofPattern("dd/MM/YYYY"),
            DateTimeFormatter.ofPattern("d/M/YYYY")
    );
    private static List<DateTimeFormatter> dateTimeFormatters = List.of(
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL),
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT),
            new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy HHmm")
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("d/M/yyyy HHmm")
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter()
    );

    /**
     * Returns a LocalDate parsed from the specified String. Attempts to use several default {@link DateTimeFormatter}s
     * to parse the String. An exception is thrown if the input is not in a recognised format.
     * <p></p>
     * If the input contains both a date and a time, {@link #parseDateTime(String)} should be used instead.
     *
     * @param input           the String to be parsed. It must contain only a date and be in a recognised format.
     * @return                the LocalDate parsed from the input
     * @throws DukeException  if the String cannot be parsed
     */
    public static LocalDate parseDate(String input) throws DukeException {
        LocalDate date = null;
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Format not applicable
            }
        }
        if (date == null) {
            throw new DukeException("Could not parse input as date");
        }
        return date;
    }

    /**
     * Returns a LocalDateTime parsed from the specified String. Attempts to use several default
     * {@link DateTimeFormatter}s to parse the String. An exception is thrown if the input is not in a
     * recognised format.
     * <p></p>
     * If the input contains only a date but not a time, {@link #parseDate(String)} should be used instead.
     *
     * @param input           the String to be parsed. It must contain both a date and a time
     *                        and be in a recognised format.
     * @return                the LocalDateTime parsed from the input
     * @throws DukeException  if the String cannot be parsed
     */
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        LocalDateTime dateTime = null;
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                dateTime = LocalDateTime.parse(input, formatter);
                break;
            } catch (DateTimeParseException e) {
                // Format not applicable
            }
        }
        if (dateTime == null) {
            throw new DukeException("Could not parse input as date");
        }
        return dateTime;
    }

    /**
     * Returns a String representing a verbosely formatted version of the specified date or datetime String. It first
     * attempts to parse the input as a {@link LocalDate}. If that fails, it tries to parse it as a
     * {@link LocalDateTime}. Upon successful parsing, it prettily formats the resulting object as a String.
     * <p></p>
     * LocalDates are formatted as <code>"d of MMMM yyyy"</code>.
     *
     * <p>LocalDateTimes are formatted as <code>"d of MMMM yyyy, h:mma"</code>.
     *
     * @param input           the String to be parsed. It can be either a date or datetime but must be in a
     *                        recognised format.
     * @return                String containing the prettily formatted date or datetime
     * @throws DukeException  if the input cannot be parsed as either a LocalDate or LocalDateTime
     */
    public static String prettifyDateOrDateTimeString(String input) throws DukeException {
        String parsedString = null;
        try {
            parsedString = parseDate(input).format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy"));
        } catch (DukeException e1) {
            try {
                parsedString = parseDateTime(input).format(DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, h:mma"));
            } catch (DukeException e2) {
                // Cannot be parsed with the default formatters
            }
        }
        if (parsedString == null) {
            throw new DukeException("Could not parse String as Date or DateTime");
        }
        return parsedString;
    }
}

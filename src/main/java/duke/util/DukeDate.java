package duke.util;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for representing dates or datetimes regardless of whether they are in a recognised, parsable format or not.
 */
public class DukeDate {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM YYYY");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM YYYY h:mma");

    private LocalDateTime localDateTime;
    private boolean hasTime;
    private String displayString;

    /**
     * Constructs a DukeDate from the specified LocalDateTime.
     *
     * @param localDateTime  the LocalDateTime to represent
     */
    public DukeDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.hasTime = true;
        this.displayString = localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a DukeDate from the specified LocalDate.
     *
     * @param localDate  the LocalDate to represent
     */
    public DukeDate(LocalDate localDate) {
        this.localDateTime = localDate.atStartOfDay();
        this.hasTime = false;
        this.displayString = localDateTime.format(DATE_FORMATTER);
    }

    /**
     * Constructs a DukeDate with the specified string. The string is assumed
     * to be an unparsable date and will be stored without further processing.
     *
     * @param dateString  String representing an unparsable date
     */
    private DukeDate(String dateString) {
        this.localDateTime = null;
        this.hasTime = false;
        this.displayString = dateString;
    }

    /**
     * Returns a DukeDate representing the specified date string. It first attempts to parse the input
     * as a {@link LocalDate}. If that fails, it tries to parse it as a {@link LocalDateTime}. If the string
     * cannot be parsed, it is stored as a raw string.
     *
     * @param text  the date string to represent
     * @return  DukeDate representing the specified date string
     */
    public static DukeDate parse(String text) {
        try {
            LocalDate localDate = DateParser.parseDate(text);
            return new DukeDate(localDate);
        } catch (DukeException e1) {
            try {
                LocalDateTime localDateTime = DateParser.parseDateTime(text);
                return new DukeDate(localDateTime);
            } catch (DukeException e2) {
                // Cannot be parsed
            }
        }
        return new DukeDate(text);
    }

    /**
     * Returns a string representation of this DukeDate.
     * <p></p>
     * Dates are formatted as <code>"d of MMMM yyyy"</code>.
     * <p>Datetimes are formatted as <code>"d of MMMM yyyy, h:mma"</code>.
     * <p>Unparsable dates are formatted as they were given.
     *
     * @return  a string representation of this DukeDate
     */
    @Override
    public String toString() {
        return displayString;
    }
}

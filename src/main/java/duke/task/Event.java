package duke.task;

import duke.util.DukeDate;

/**
 * Task consisting of a description and a date.
 */
public class Event extends Task {
    /**
     * The date of this event.
     * DukeDate allows the user to input custom date strings such as 'the day after tomorrow'
     */
    protected DukeDate date;

    public Event(String desc, String dateString) {
        super(desc);
        this.date = DukeDate.parse(dateString);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, date.toString());
    }
}

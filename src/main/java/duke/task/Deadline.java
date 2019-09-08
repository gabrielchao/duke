package duke.task;

import duke.util.DukeDate;

/**
 * Deadline task consisting of a description and a deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline this task must be completed by.
     * DukeDate allows the user to input custom date strings such as 'the day after tomorrow'
     */
    protected DukeDate date;

    public Deadline(String desc, String dateString) {
        super(desc);
        this.date = DukeDate.parse(dateString);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date.toString());
    }
}

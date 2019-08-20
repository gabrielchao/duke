package duke.tasks;

import duke.Task;

public class Deadline extends Task {
    protected String date;
    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", getStatusChar(), description, date);
    }
}
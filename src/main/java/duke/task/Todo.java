package duke.task;

/**
 * Task consisting of a description only.
 */
public class Todo extends Task {
    public Todo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }
}

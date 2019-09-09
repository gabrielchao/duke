package duke;

/**
 * General exception for Duke-related issues.
 */
public class DukeException extends Exception {
    public DukeException(String s) {
        super(s);
    }

    public DukeException(Throwable t) {
        super(t);
    }
}

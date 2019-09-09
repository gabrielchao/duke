package duke.io;

/**
 * A variable-length buffer for UI output strings.
 *
 * <p>Instances of <code>BufferedStringOutput</code> are not safe for use by multiple threads.
 */
public class BufferedStringOutput implements UiOutput {
    public static final String OOPS_PREFIX = "\u2639 OOPS!!! "; // ☹

    private StringBuilder sb = new StringBuilder();

    public void say(String text) {
        sb.append(text).append('\n');
    }

    public void oops(String text) {
        sb.append(OOPS_PREFIX).append(text).append('\n');
    }

    /**
     * Returns a concatenated string consisting of all strings written to this <code>BufferedStringOutput</code>
     * since the previous call to <code>nextResponse</code>. Each output string is separated by a newline.
     *
     * <p>The buffer is cleared after this method is called.
     *
     * @return  string concatenated from all strings in this buffer
     */
    public String nextResponse() {
        String response = sb.toString();
        sb = new StringBuilder();
        return response;
    }
}

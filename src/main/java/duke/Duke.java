package duke;

import duke.command.Command;
import duke.io.BufferedStringOutput;
import duke.task.TaskList;
import duke.util.PreParser;

import java.io.IOException;

/**
 * Main logic class.
 */
public class Duke {
    public final String DEFAULT_FILEPATH = "./data/tasks.txt";
    public final String LOADING_ERROR_MESSAGE = "Couldn't load tasks from disk."
            + "\nYour changes this session may not be saved!";
    public final String WELCOME_MESSAGE = "Hello! I'm Duke."
            + "\nWhat can I do for you?";

    private PreParser preParser;
    private TaskList tasks;
    private Storage storage;
    private BufferedStringOutput bufferedUiOutput = new BufferedStringOutput();
    private BufferedStringOutput startupMessages = new BufferedStringOutput();

    /**
     * Constructs a Duke object with the data file residing in the default path.
     */
    public Duke() {
        try {
            preParser = new PreParser();
        } catch (DukeException e) {
            e.printStackTrace();
            startupMessages.oops("I couldn't understand my list of commands! "
                    + "I won't be very helpful this session :(");
        }
        storage = new Storage(DEFAULT_FILEPATH);
        try {
            tasks = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
            startupMessages.oops("Couldn't load tasks from disk!");
        }
    }

    /**
     * Generates Duke's response to user input.
     */
    public String getResponse(String input) {
        try {
            Command command = preParser.parse(input);
            command.execute(tasks, bufferedUiOutput, storage);
        } catch (DukeException e) {
            bufferedUiOutput.oops(e.getMessage());
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            // Unable to save
        }

        return bufferedUiOutput.nextResponse();
    }
}

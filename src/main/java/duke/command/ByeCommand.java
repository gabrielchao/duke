package duke.command;

import duke.Storage;
import duke.io.UiOutput;
import duke.task.TaskList;

/**
 * When executed, outputs a goodbye message and signals the app to exit.
 */
public class ByeCommand extends Command {
    public ByeCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "bye";
    }

    @Override
    public void execute(TaskList tasks, UiOutput uiOutput, Storage storage) {
        uiOutput.say("Bye. Hope to see you again soon!");
    }
}

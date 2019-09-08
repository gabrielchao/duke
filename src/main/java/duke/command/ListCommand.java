package duke.command;

import duke.Storage;
import duke.io.UiOutput;
import duke.task.TaskList;

/**
 * When executed, lists tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void execute(TaskList tasks, UiOutput uiOutput, Storage storage) {
        if (tasks.size() > 0) {
            uiOutput.say(tasks.toString());
        } else {
            uiOutput.say("No tasks yet.");
        }
    }
}

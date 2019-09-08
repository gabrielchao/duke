package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.io.UiOutput;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Map;

/**
 * When executed, removes the specified task.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void execute(TaskList tasks, UiOutput uiOutput, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = argumentParser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("An index must be specified.");
        }

        int oneIndex;
        try {
            oneIndex = Integer.parseInt(comArgs[0]);
        } catch (NumberFormatException e) {
            throw new DukeException("The index to be deleted must be an integer.");
        }
        Task task;
        try {
            task = tasks.delete(oneIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with index " + oneIndex + ".");
        }
        uiOutput.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                task.toString(), tasks.size(), tasks.size() == 1 ? "" : "s"));
    }
}

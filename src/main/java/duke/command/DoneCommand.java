package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.io.UiOutput;
import duke.task.TaskList;

import java.util.Map;

/**
 * When executed, marks a specified task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "done";
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
            throw new DukeException("The index to be marked must be an integer.");
        }
        try {
            tasks.markDone(oneIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with index " + oneIndex + ".");
        }
        uiOutput.say("Nice! I've marked this task as done:\n" + tasks.get(oneIndex));
    }
}

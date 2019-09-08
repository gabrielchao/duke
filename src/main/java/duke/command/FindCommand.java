package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.io.UiOutput;
import duke.task.TaskList;

import java.util.List;
import java.util.Map;

/**
 * Finds tasks that contain a given keyword.
 */
public class FindCommand extends Command {
    public FindCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "find";
    }

    @Override
    public void execute(TaskList tasks, UiOutput uiOutput, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = argumentParser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The search keyword cannot be blank.");
        }

        List<Integer> foundTaskIndices = tasks.findKeywordOneIndices(comArgs[0], false);
        if (foundTaskIndices.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < foundTaskIndices.size(); i++) {
                int oneIndex = foundTaskIndices.get(i);
                sb.append(oneIndex)
                        .append(". ")
                        .append(tasks.get(oneIndex).toString());
                if (i != foundTaskIndices.size() - 1) {
                    sb.append("\n");
                }
            }
            uiOutput.say("Here are the matching tasks in your list:\n" + sb.toString());
        } else {
            uiOutput.say("There are no matching tasks in your list.");
        }
    }
}

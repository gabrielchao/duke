package duke.command;

import duke.DukeException;
import duke.io.UiOutput;
import duke.task.Todo;
import duke.util.ArgumentParser;
import duke.Storage;
import duke.task.TaskList;

import java.util.Map;

/**
 * When executed, adds a new todo to the <code>TaskList</code>.
 */
public class TodoCommand extends Command {
    public TodoCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "todo";
    }

    @Override
    public void execute(TaskList tasks, UiOutput uiOutput, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = argumentParser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        assert comArgs != null : "comArgs cannot be null";
        if (comArgs.length == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo t = new Todo(ArgumentParser.concatenate(comArgs));
        tasks.add(t);
        uiOutput.say("added: " + t);
    }
}

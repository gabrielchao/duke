package duke.command;

import duke.DukeException;
import duke.util.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Map;

public class TodoCommand extends Command {
    public TodoCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "todo";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        duke.task.Todo t = new duke.task.Todo(Parser.concatenate(comArgs));
        tasks.add(t);
        ui.say("added: " + t);
    }
}

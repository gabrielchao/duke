package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.util.ArgumentParser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Map;

public class EventCommand extends Command {

    public EventCommand(String[] args) {
        super(args);

        argumentParser.register("/at", true);
    }

    @Override
    public String getName() {
        return "event";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = argumentParser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if (comArgs.length == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] atArgs = switchArgs.get("/at");
        if (atArgs.length == 0) {
            throw new DukeException("The date of an event cannot be empty.");
        }

        Event e = new Event(ArgumentParser.concatenate(comArgs), ArgumentParser.concatenate(atArgs));
        tasks.add(e);
        ui.say("added: " + e);
    }
}

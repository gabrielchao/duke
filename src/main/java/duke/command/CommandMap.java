package duke.command;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    Map<String, Class<? extends Command>> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command.getClass());
    }

    public Class<? extends Command> get(String name) {
        return commands.get(name);
    }
}

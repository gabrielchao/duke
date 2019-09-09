package duke.command;

import duke.DukeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map between Command names and Command classes. Can be used during parsing to
 * instantiate Commands.
 */
public class CommandMap {
    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    /**
     * Constructs an empty CommandMap.
     */
    public CommandMap() {
        // Empty constructor
    }

    /**
     * Constructs a CommandMap with the specified Command {@link Class} objects.
     *
     * @param commandList  list of Command Class objects to insert into this CommandMap
     * @throws DukeException  if a Command cannot be {@link #register(Class)}ed.
     */
    public CommandMap(List<Class<? extends Command>> commandList) throws DukeException {
        for(Class<? extends Command> cls : commandList) {
            this.register(cls);
        }
    }

    /**
     * Inserts the specified Command type into this <code>CommandMap</code>. The values returned by
     * {@link Command#getName()} and {@link Command#getClass()} are used as the key and value
     * of the entry respectively.
     * <p></p>
     * The <code>Command</code> may be instantiated with dummy arguments. The dummy <code>Command</code>
     * is not stored and can be safely discarded afterward.
     *
     * @param command  the Command to insert
     */
    public void register(Command command) {
        commands.put(command.getName(), command.getClass());
    }

    /**
     * Inserts the specified Command type into this <code>CommandMap</code>. The value of {@link Command#getName()}
     * is used as the key of the entry while the specified <code>commandClass</code> is used as the value.
     * <p></p>
     * Because Java does not support abstract static methods, the only way to access the abstract method
     * <code>getName</code> is to instantiate an object of the class, which this method accomplishes via reflection,
     * passing the {@link Command#Command(String[])} constructor a dummy argument.
     *
     * @param commandClass  the Command Class to insert
     * @throws DukeException  if the dummy Command cannot be instantiated
     */
    public void register(Class<? extends Command> commandClass) throws DukeException {
        Command command;
        try {
            command = commandClass.getConstructor(String[].class).newInstance(new Object[]{new String[]{}});
        } catch (Exception e) {
            // This should never happen
            e.printStackTrace();
            throw new DukeException(e);
        }
        commands.put(command.getName(), commandClass);
    }

    /**
     * Returns the <code>Command</code> <code>Class</code> corresponding to the specified <code>Command</code> name,
     * or <code>null</code> if this CommandMap contains no mapping for the name.
     *
     * @param name  the Command name whose associated Command is to be returned
     * @return  the Command Class to which the name is mapped, or <code>null</code> if this CommandMap contains no
     *          mapping for the key
     */
    public Class<? extends Command> get(String name) {
        return commands.get(name);
    }
}

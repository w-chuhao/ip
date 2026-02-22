package chu.command;

import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

/**
 * Command that exits the application.
 */
public class ByeCommand implements Command {
    /**
     * Prints a farewell message.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Marks this command as an exit command.
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

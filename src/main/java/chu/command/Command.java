package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

/**
 * Represents an executable user command.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If command execution fails due to user input.
     */
    void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions;

    /**
     * Indicates whether this command ends the application.
     *
     * @return {@code true} if the command is an exit command, otherwise
     *         {@code false}.
     */
    default boolean isExit() {
        return false;
    }
}

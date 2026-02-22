package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

public interface Command {
    void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions;

    default boolean isExit() {
        return false;
    }
}

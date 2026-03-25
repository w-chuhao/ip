package chu.command;

import chu.errorhandler.ChuException;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

public interface Command {
    void execute(TaskList taskList, TaskStorage storage) throws ChuException;

    default boolean isExit() {
        return false;
    }
}

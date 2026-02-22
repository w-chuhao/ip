package chu.command;

import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

public class bye implements command {
    @Override
    public void execute(TaskList taskList, TaskStorage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

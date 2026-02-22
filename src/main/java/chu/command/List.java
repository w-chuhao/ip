package chu.command;

import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

public class List implements command {
    @Override
    public void execute(TaskList taskList, TaskStorage storage) {
        System.out.println("Here are the tasks in your list:");
        taskList.printList();
    }
}

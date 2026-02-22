package chu.command;

import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

/**
 * Command that prints all tasks.
 */
public class ListCommand implements Command {
    /**
     * Prints the current list of tasks.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) {
        System.out.println("Here are the tasks in your list:");
        taskList.printList();
    }
}

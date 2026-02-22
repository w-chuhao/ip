package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

/**
 * Command that deletes a task by index.
 */
public class DeleteCommand implements Command {
    private final String[] sentence;

    /**
     * Creates a delete command.
     *
     * @param sentence Tokenized user input.
     */
    public DeleteCommand(String[] sentence) {
        this.sentence = sentence;
    }

    /**
     * Deletes the targeted task and persists changes.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If the index is invalid.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        int deleteIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Tasks deletedTask = taskList.delete(deleteIndex);
        System.out.println("Noted, I've removed this tasks:");
        System.out.println(deletedTask);
        storage.saveTasks(taskList);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

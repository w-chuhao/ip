package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand implements Command {
    private final String[] sentence;

    /**
     * Creates an unmark command.
     *
     * @param sentence Tokenized user input.
     */
    public UnmarkCommand(String[] sentence) {
        this.sentence = sentence;
    }

    /**
     * Unmarks the targeted task and persists changes.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If the index is invalid.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        int unmarkIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Tasks unmarkedTask = taskList.unmark(unmarkIndex);
        storage.saveTasks(taskList);
        System.out.println("OK, I've marked this tasks as not done yet:");
        System.out.println(unmarkedTask);
    }
}

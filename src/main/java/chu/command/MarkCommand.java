package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

/**
 * Command that marks a task as done.
 */
public class MarkCommand implements Command {
    private final String[] sentence;

    /**
     * Creates a mark command.
     *
     * @param sentence Tokenized user input.
     */
    public MarkCommand(String[] sentence) {
        this.sentence = sentence;
    }

    /**
     * Marks the targeted task as done and persists changes.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If the index is invalid.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        int markIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Tasks markedTask = taskList.mark(markIndex);
        storage.saveTasks(taskList);
        System.out.println("Nice! I've marked this tasks as done:");
        System.out.println(markedTask);
    }
}

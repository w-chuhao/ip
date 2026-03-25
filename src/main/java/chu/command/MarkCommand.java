package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Task;

public class MarkCommand implements Command {
    private final String[] sentence;

    public MarkCommand(String[] sentence) {
        this.sentence = sentence;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        int markIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Task markedTask = taskList.mark(markIndex);
        storage.saveTasks(taskList);
        System.out.println("Nice! I've marked this tasks as done:");
        System.out.println(markedTask);
    }
}

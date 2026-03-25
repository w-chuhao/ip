package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Task;

public class UnmarkCommand implements Command {
    private final String[] sentence;

    public UnmarkCommand(String[] sentence) {
        this.sentence = sentence;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        int unmarkIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Task unmarkedTask = taskList.unmark(unmarkIndex);
        storage.saveTasks(taskList);
        System.out.println("OK, I've marked this tasks as not done yet:");
        System.out.println(unmarkedTask);
    }
}

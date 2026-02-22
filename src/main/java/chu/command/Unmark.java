package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

public class Unmark implements command {
    private final String[] sentence;

    public Unmark(String[] sentence) {
        this.sentence = sentence;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        int unmarkIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Tasks unmarkedTask = taskList.unmark(unmarkIndex);
        storage.saveTasks(taskList);
        System.out.println("OK, I've marked this tasks as not done yet:");
        System.out.println(unmarkedTask);
    }
}

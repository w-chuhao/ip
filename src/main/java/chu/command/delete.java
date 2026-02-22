package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

public class delete implements command {
    private final String[] sentence;

    public delete(String[] sentence) {
        this.sentence = sentence;
    }

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

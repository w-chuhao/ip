package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Task;

public class DeleteCommand implements Command {
    private final String[] sentence;

    public DeleteCommand(String[] sentence) {
        this.sentence = sentence;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        int deleteIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Task deletedTask = taskList.delete(deleteIndex);
        System.out.println("Noted, I've removed this tasks:");
        System.out.println(deletedTask);
        storage.saveTasks(taskList);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

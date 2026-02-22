package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Deadlines;
import chu.tasks.Tasks;

public class deadline implements command {
    private final String line;

    public deadline(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        String[] deadline = ErrorHandler.handleDeadlines(line);
        Tasks deadlineTask = new Deadlines(deadline[0], deadline[1]);
        taskList.add(deadlineTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

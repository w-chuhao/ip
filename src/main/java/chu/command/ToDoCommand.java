package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Task;
import chu.tasks.Todo;

public class ToDoCommand implements Command {
    private final String line;

    public ToDoCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        String toDo = ErrorHandler.handleTodos(line);
        Task todoTask = new Todo(toDo);
        taskList.add(todoTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(todoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

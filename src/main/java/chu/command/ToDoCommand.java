package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;
import chu.tasks.ToDos;

/**
 * Command that creates a new to-do task.
 */
public class ToDoCommand implements Command {
    private final String line;

    /**
     * Creates a to-do command.
     *
     * @param line Raw user input.
     */
    public ToDoCommand(String line) {
        this.line = line;
    }

    /**
     * Adds a to-do task and persists changes.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If the task description is invalid.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        String toDo = ErrorHandler.handleToDos(line);
        Tasks todoTask = new ToDos(toDo);
        taskList.add(todoTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(todoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.parser.DateTimeParser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Deadlines;
import chu.tasks.Tasks;

import java.time.LocalDateTime;

/**
 * Command that creates a new deadline task.
 */
public class DeadlineCommand implements Command {
    private final String line;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Creates a deadline command.
     *
     * @param line User input.
     */
    public DeadlineCommand(String line) {
        this.line = line;
    }

    /**
     * Adds a deadline task and persists changes.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If command or date-time input is invalid.
     */
    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        String[] deadline = ErrorHandler.handleDeadlines(line);
        LocalDateTime by = dateTimeParser.parseInput(deadline[1]);
        Tasks deadlineTask = new Deadlines(deadline[0], by);
        taskList.add(deadlineTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

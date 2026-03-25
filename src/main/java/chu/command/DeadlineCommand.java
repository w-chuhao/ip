package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.parser.DateTimeParser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Deadline;
import chu.tasks.Task;

import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private final String line;
    private final DateTimeParser dateTimeParser = new DateTimeParser();

    public DeadlineCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        String[] deadline = ErrorHandler.handleDeadlines(line);
        LocalDateTime by = dateTimeParser.parseInput(deadline[1]);
        Task deadlineTask = new Deadline(deadline[0], by);
        taskList.add(deadlineTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

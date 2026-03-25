package chu.command;

import chu.errorhandler.ChuException;
import chu.errorhandler.ErrorHandler;
import chu.parser.DateTimeParser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Event;
import chu.tasks.Task;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private final String line;
    private final DateTimeParser dateTimeParser = new DateTimeParser();

    public EventCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuException {
        String[] event = ErrorHandler.handleEvents(line);
        LocalDateTime from = dateTimeParser.parseInput(event[1]);
        LocalDateTime to = dateTimeParser.parseInput(event[2]);
        Task eventTask = new Event(event[0], from, to);
        taskList.add(eventTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

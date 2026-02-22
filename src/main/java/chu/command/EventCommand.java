package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.parser.DateTimeParser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Events;
import chu.tasks.Tasks;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private final String line;
    private DateTimeParser dateTimeParser = new DateTimeParser();

    public EventCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        String[] event = ErrorHandler.handleEvents(line);
        LocalDateTime from = dateTimeParser.parseInput(event[1]);
        LocalDateTime to = dateTimeParser.parseInput(event[2]);
        Tasks eventTask = new Events(event[0], from, to);
        taskList.add(eventTask);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this tasks:");
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}

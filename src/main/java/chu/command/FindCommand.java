package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final String line;

    public FindCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        String keyword = line.length() <= 4 ? "" : line.substring(4).trim();
        ArrayList<Tasks> result = ErrorHandler.handleFind(keyword, taskList);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < result.size(); i += 1) {
            System.out.println((i + 1) + "." + result.get(i));
        }
    }
}

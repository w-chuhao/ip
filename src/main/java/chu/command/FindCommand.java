package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

import java.util.ArrayList;

/**
 * Command that finds tasks containing a keyword.
 */
public class FindCommand implements Command {
    private final String line;

    /**
     * Creates a find command.
     *
     * @param line Raw user input.
     */
    public FindCommand(String line) {
        this.line = line;
    }

    /**
     * Finds and prints tasks that match the keyword.
     *
     * @param taskList In-memory task list.
     * @param storage Storage handler for persistence.
     * @throws ChuExceptions If the keyword is invalid or no match is found.
     */
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

package chu.errorHandler;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;
import java.util.ArrayList;

/**
 * Performs validation and parsing checks for user input.
 */
public class ErrorHandler {

    /**
     * Validates that the input line is not empty.
     *
     * @param line Input line to validate.
     * @throws ChuExceptions If the line is empty.
     */
    public static void handleEmpty(String line) throws ChuExceptions {
        if (line.trim().isEmpty()) {
            throw new ChuExceptions("Please enter a command.");
        }
    }

    /**
     * Finds tasks whose descriptions contain the keyword.
     *
     * @param word Keyword to search for.
     * @param taskList Task list to search in.
     * @return Matching tasks.
     * @throws ChuExceptions If the keyword is empty or no task is found.
     */
    public static ArrayList<Tasks> handleFind(String word, TaskList taskList) throws ChuExceptions {
        if (word == null || word.trim().isEmpty()) {
            throw new ChuExceptions("The keyword for find cannot be empty.");
        }
        word = word.toLowerCase();
        ArrayList<Tasks> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i += 1) {
            Tasks current = taskList.getTask(i);
            String description = current.getDescription().toLowerCase();
            if (description.contains(word)) {
                result.add(current);
            }
        }
        if (result.isEmpty()) {
            throw new ChuExceptions("Task not found");
        }
        return result;
    }

    /**
     * Parses and validates a user-provided task index.
     *
     * @param sentence Tokenized user command.
     * @param counter Total number of tasks.
     * @return Zero-based validated task index.
     * @throws ChuExceptions If index is missing, not numeric, or out of range.
     */
    public static int handleIndex(String[] sentence, int counter) throws ChuExceptions {
        if (sentence.length < 2) {
            throw new ChuExceptions("Please provide an index, e.g. `mark 2`, `unmark 2`.");
        }
        int index;
        try {
            index = Integer.parseInt(sentence[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ChuExceptions("Index must be a number.");
        }
        if (index < 0 || index >= counter) {
            throw new ChuExceptions("Index out of range.");
        }
        return index;
    }

    /**
     * Extracts a to-do description from the command line.
     *
     * @param line Raw to-do command line.
     * @return Trimmed to-do description.
     * @throws ChuExceptions If the description is empty.
     */
    public static String handleToDos(String line) throws ChuExceptions {
        if (line.length() <= 4 || line.substring(4).trim().isEmpty()) {
            throw new ChuExceptions("The description of a todo cannot be empty.");
        }
        return line.substring(5).trim();
    }

    /**
     * Extracts deadline description and due time from the command line.
     *
     * @param line Raw deadline command line.
     * @return Array containing description and due time text.
     * @throws ChuExceptions If command parts are missing or empty.
     */
    public static String[] handleDeadlines(String line) throws ChuExceptions {
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new ChuExceptions("Deadline needs a /by, e.g. `deadline read /by Monday`.");
        }
        String description = line.substring(9, byIndex).trim();
        String by = line.substring(byIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChuExceptions("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new ChuExceptions("The /by value cannot be empty.");
        }
        return new String[] { description, by };
    }

    /**
     * Extracts event description, start time and end time from the command.
     *
     * @param line Raw event command line.
     * @return Array containing description, start and end time text.
     * @throws ChuExceptions If command format is invalid or values are empty.
     */
    public static String[] handleEvents(String line) throws ChuExceptions {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new ChuExceptions("Event needs /from and /to, e.g. `event meet /from 2pm /to 3pm`.");
        }
        String description = line.substring(6, fromIndex).trim();
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChuExceptions("The description of an event cannot be empty.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new ChuExceptions("The /from and /to values cannot be empty.");
        }
        return new String[] { description, from, to };
    }

}

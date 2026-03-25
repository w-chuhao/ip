package chu.errorhandler;

import chu.tasklist.TaskList;
import chu.tasks.Task;

import java.util.ArrayList;

public class ErrorHandler {
    public static void handleEmpty(String line) throws ChuException {
        if (line.trim().isEmpty()) {
            throw new ChuException("Please enter a command.");
        }
    }

    public static ArrayList<Task> handleFind(String word, TaskList taskList) throws ChuException {
        if (word == null || word.trim().isEmpty()) {
            throw new ChuException("The keyword for find cannot be empty.");
        }
        word = word.toLowerCase();
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i += 1) {
            Task current = taskList.getTask(i);
            String description = current.getDescription().toLowerCase();
            if (description.contains(word)) {
                result.add(current);
            }
        }
        if (result.isEmpty()) {
            throw new ChuException("Task not found");
        }
        return result;
    }

    public static int handleIndex(String[] sentence, int counter) throws ChuException {
        if (sentence.length < 2) {
            throw new ChuException("Please provide an index, e.g. `mark 2`, `unmark 2`.");
        }
        int index;
        try {
            index = Integer.parseInt(sentence[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ChuException("Index must be a number.");
        }
        if (index < 0 || index >= counter) {
            throw new ChuException("Index out of range.");
        }
        return index;
    }

    public static String handleTodos(String line) throws ChuException {
        if (line.length() <= 4 || line.substring(4).trim().isEmpty()) {
            throw new ChuException("The description of a todo cannot be empty.");
        }
        return line.substring(5).trim();
    }

    public static String[] handleDeadlines(String line) throws ChuException {
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new ChuException("Deadline needs a /by, e.g. `deadline read /by Monday`.");
        }
        String description = line.substring(9, byIndex).trim();
        String by = line.substring(byIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChuException("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new ChuException("The /by value cannot be empty.");
        }
        return new String[] { description, by };
    }

    public static String[] handleEvents(String line) throws ChuException {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new ChuException("Event needs /from and /to, e.g. `event meet /from 2pm /to 3pm`.");
        }
        String description = line.substring(6, fromIndex).trim();
        String from = line.substring(fromIndex + 5, toIndex).trim();
        String to = line.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChuException("The description of an event cannot be empty.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new ChuException("The /from and /to values cannot be empty.");
        }
        return new String[] { description, from, to };
    }
}

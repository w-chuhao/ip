package chu.errorHandler;

public class ErrorHandler {

    public static void handleEmpty(String line) throws ChuExceptions {
        if (line.trim().isEmpty()) {
            throw new ChuExceptions("Please enter a command.");
        }
    }

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

    public static String handleToDos(String line) throws ChuExceptions {
        if (line.length() <= 4 || line.substring(4).trim().isEmpty()) {
            throw new ChuExceptions("The description of a todo cannot be empty.");
        }
        return line.substring(5).trim();
    }

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

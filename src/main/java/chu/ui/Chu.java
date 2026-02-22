package chu.ui;

import chu.command.Command;
import chu.errorHandler.ChuExceptions;
import chu.parser.Parser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

/**
 * Entry point for the Chu task manager application.
 */
public class Chu {
    /**
     * Starts the application loop and handles user commands.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        String logo =
                "  _____ _    _ _   _ \n"
                        + " / ____| |  | | | | |\n"
                        + "| |    | |__| | | | |\n"
                        + "| |    |  __  | | | |\n"
                        + "| |____| |  | | |_| |\n"
                        + " \\_____|_|  |_|\\___/ \n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");

        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskStorage storage = new TaskStorage();
        TaskList taskList = new TaskList();
        try {
            storage.initStorage();
            taskList = storage.loadTasks();
        } catch (Exception e) {
            System.out.println("Unable to initialize storage.");
        }

        while (true) {
            try {
                String line = ui.readLine();
                Command userCommand = parser.parse(line);
                userCommand.execute(taskList, storage);
                if (userCommand.isExit()) {
                    return;
                }
            } catch (ChuExceptions e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }
        }
    }
}

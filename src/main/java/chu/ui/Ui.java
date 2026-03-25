package chu.ui;

import chu.command.Command;
import chu.errorhandler.ChuException;
import chu.parser.Parser;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;

import java.util.Scanner;

/**
 * Handles user input from standard input.
 */
public class Ui {
    private static final String LOGO =
            "  _____ _    _ _   _ \n"
                    + " / ____| |  | | | | |\n"
                    + "| |    | |__| | | | |\n"
                    + "| |    |  __  | | | |\n"
                    + "| |____| |  | | |_| |\n"
                    + " \\_____|_|  |_|\\___/ \n";

    private final Scanner scanner;
    private final Parser parser;
    private final TaskStorage storage;
    private TaskList taskList;

    /**
     * Constructs a UI instance with a scanner bound to standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser();
        this.storage = new TaskStorage();
        this.taskList = new TaskList();
    }

    /**
     * Starts the application, prints the greeting, and processes commands.
     */
    public void run() {
        showWelcome();
        initializeStorage();

        while (true) {
            try {
                String line = readLine();
                Command userCommand = parser.parse(line);
                userCommand.execute(taskList, storage);
                if (userCommand.isExit()) {
                    return;
                }
            } catch (ChuException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }
        }
    }

    /**
     * Reads one line from user input.
     *
     * @return The input line entered by the user.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    private void showWelcome() {
        System.out.println("Hello I'm\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    private void initializeStorage() {
        try {
            storage.initStorage();
            taskList = storage.loadTasks();
        } catch (Exception e) {
            System.out.println("Unable to initialize storage.");
        }
    }
}

package chu.ui;

import java.util.Scanner;

/**
 * Handles user input from standard input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a UI instance with a scanner bound to standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads one line from user input.
     *
     * @return The input line entered by the user.
     */
    public String readLine() {
        return scanner.nextLine();
    }
}

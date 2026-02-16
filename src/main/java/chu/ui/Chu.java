package chu.ui;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasks.Deadlines;
import chu.tasks.Events;
import chu.tasks.Tasks;
import chu.tasks.ToDos;

import java.util.ArrayList;
import java.util.Scanner;

public class Chu {
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

        Scanner in = new Scanner(System.in);
        TaskStorage storage = new TaskStorage();
        ArrayList<Tasks> task = new ArrayList<>();
        try {
            storage.initStorage();
            task = storage.loadTasks();
        } catch (Exception e) {
            System.out.println("Unable to initialize storage.");
        }
        int index = 0;

        while (true) {
            try {
                String line = in.nextLine();
                ErrorHandler.handleEmpty(line);
                line = line.trim().toLowerCase();

                String[] sentence = line.split(" ");
                String command = sentence[0];

                switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < task.size(); i++) {
                        System.out.println((i + 1) + "." + task.get(i));
                    }
                    break;
                case "mark":
                    index = ErrorHandler.handleIndex(sentence, task.size());
                    task.get(index).markAsDone();
                    storage.saveTasks(task);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.get(index));
                    break;

                case "unmark":
                    index = ErrorHandler.handleIndex(sentence, task.size());
                    task.get(index).unmark();
                    storage.saveTasks(task);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.get(index));
                    break;

                case "todo":
                    String toDo = ErrorHandler.handleToDos(line);
                    task.add(new ToDos(toDo));
                    storage.saveTasks(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    break;

                case "deadline":
                    String[] deadline = ErrorHandler.handleDeadlines(line);
                    task.add(new Deadlines(deadline[0], deadline[1]));
                    storage.saveTasks(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    break;

                case "event":
                    String[] event = ErrorHandler.handleEvents(line);
                    task.add(new Events(event[0], event[1], event[2]));
                    storage.saveTasks(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " tasks in the list.");
                    break;

                default:
                    throw new ChuExceptions("Invalid command. Valid commands: bye, list, todo, deadline, event, mark, unmark.");
                }

            } catch (ChuExceptions e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }
        }
    }
}

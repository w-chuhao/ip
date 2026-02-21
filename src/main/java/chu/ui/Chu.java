package chu.ui;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Deadlines;
import chu.tasks.Events;
import chu.tasks.Tasks;
import chu.tasks.ToDos;

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
        TaskList taskList = new TaskList();
        try {
            storage.initStorage();
            taskList = storage.loadTasks();
        } catch (Exception e) {
            System.out.println("Unable to initialize storage.");
        }

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
                    taskList.printList();
                    break;

                case "delete":
                    int deleteIndex = ErrorHandler.handleIndex(sentence, taskList.size());
                    Tasks deletedTask = taskList.delete(deleteIndex);
                    System.out.println("Noted, I've removed this tasks:");
                    System.out.println(deletedTask);
                    storage.saveTasks(taskList);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    break;

                case "mark":
                    int markIndex = ErrorHandler.handleIndex(sentence, taskList.size());
                    Tasks markedTask = taskList.mark(markIndex);
                    storage.saveTasks(taskList);
                    System.out.println("Nice! I've marked this tasks as done:");
                    System.out.println(markedTask);
                    break;

                case "unmark":
                    int unmarkIndex = ErrorHandler.handleIndex(sentence, taskList.size());
                    Tasks unmarkedTask = taskList.unmark(unmarkIndex);
                    storage.saveTasks(taskList);
                    System.out.println("OK, I've marked this tasks as not done yet:");
                    System.out.println(unmarkedTask);
                    break;

                case "todo":
                    String toDo = ErrorHandler.handleToDos(line);
                    Tasks todoTask = new ToDos(toDo);
                    taskList.add(todoTask);
                    storage.saveTasks(taskList);
                    System.out.println("Got it. I've added this tasks:");
                    System.out.println(todoTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    break;

                case "deadline":
                    String[] deadline = ErrorHandler.handleDeadlines(line);
                    Tasks deadlineTask = new Deadlines(deadline[0], deadline[1]);
                    taskList.add(deadlineTask);
                    storage.saveTasks(taskList);
                    System.out.println("Got it. I've added this tasks:");
                    System.out.println(deadlineTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    break;

                case "event":
                    String[] event = ErrorHandler.handleEvents(line);
                    Tasks eventTask = new Events(event[0], event[1], event[2]);
                    taskList.add(eventTask);
                    storage.saveTasks(taskList);
                    System.out.println("Got it. I've added this tasks:");
                    System.out.println(eventTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
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

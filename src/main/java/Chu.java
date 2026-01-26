import java.util.Scanner;
import java.util.Arrays;

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
        Task[] list = new Task[100];
        int counter = 0;
        int index = 0;

        while (true) {
            String line = in.nextLine().trim();

            String[] sentence = line.split(" ");

            switch (sentence[0].toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ".[" + list[i].getStatusIcon() +"] "+ list[i].getDescription());
                }
                break;
            case "mark":
                if(sentence[1] != null){
                    index = Integer.parseInt(sentence[1])-1;
                }
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + list[index].getStatusIcon() +"] "+ list[index].getDescription());
                break;

            case "unmark":
                if(sentence[1] != null){
                    index = Integer.parseInt(sentence[1])-1;
                }
                list[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + list[index].getStatusIcon() +"] "+ list[index].getDescription());
                break;

            default:
                System.out.println("added: " + line);
                list[counter] = new Task(line);
                counter += 1;
                break;
            }
        }
    }
}


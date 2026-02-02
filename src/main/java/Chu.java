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
        Tasks[] task = new Tasks[100];

        int counter = 0;
        int index = 0;

        while (true) {
            String line = in.nextLine().trim().toLowerCase();

            String[] sentence = line.split(" ");

            switch (sentence[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + task[i]);
                }
                break;
            case "mark":
                if(sentence[1] != null){
                    index = Integer.parseInt(sentence[1])-1;
                }
                task[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task[index]);
                break;

            case "unmark":
                if(sentence[1] != null){
                    index = Integer.parseInt(sentence[1])-1;
                }
                task[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[index]);
                break;

            case "todo":
                task[counter] = new ToDos(line.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(task[counter]);
                counter+=1;
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            case "deadline":
                int byIndex = line.indexOf("/by");
                String description_deadline = line.substring(9, byIndex).trim();
                String by = line.substring(byIndex + 3).trim();
                task[counter] = new Deadlines(description_deadline,by);
                System.out.println("Got it. I've added this task:");
                System.out.println(task[counter]);
                counter+=1;
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            case "event":
                int fromIndex = line.indexOf("/from");
                int toIndex = line.indexOf("/to");
                String description_event = line.substring(6, fromIndex).trim();
                String from  = line.substring(fromIndex + 5,toIndex).trim();
                String to  = line.substring(toIndex + 3).trim();
                task[counter] = new Events(description_event,from,to);
                System.out.println("Got it. I've added this task:");
                System.out.println(task[counter]);
                counter+=1;
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            default:
                System.out.println("added: " + line);
                task[counter] = new Tasks(line);
                counter += 1;
                break;
            }
        }
    }
}


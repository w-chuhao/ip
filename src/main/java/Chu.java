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
        String line;
        Scanner in = new Scanner(System.in);
        String [] list = new String[100];
        int counter = 0;
        line = in.nextLine();
        while(!(line.equalsIgnoreCase("bye"))){
            if(line.equalsIgnoreCase("list")){
                for(int i=0;i<counter;i+=1){
                    System.out.println((i+1) +". "+ list[i]);
                }
            }
            else{
                System.out.println("added " + line);
                list[counter] = line;
                counter+=1;
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


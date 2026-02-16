package chu.storage;

import chu.tasks.Deadlines;
import chu.tasks.Events;
import chu.tasks.Tasks;
import chu.tasks.ToDos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    private static final String DATA_FILE_PATH = "data/chu.txt";

    public void initStorage() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);
        File parentDirectory = dataFile.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
    }

    public ArrayList<Tasks> loadTasks() throws IOException {
        ArrayList<Tasks> task = new ArrayList<>();

        File dataFile = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                task.add(parseLine(line));
            }
        }
        scanner.close();
        return task;
    }

    public void saveTasks(ArrayList<Tasks> task) throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_FILE_PATH);
        for (Tasks currentTask : task) {
            fileWriter.write(formatTask(currentTask) + System.lineSeparator());
        }
        fileWriter.close();
    }

    private Tasks parseLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        String status = parts[1];
        String description = parts[2];
        Tasks task;

        switch (type) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            task = new Deadlines(description, parts[3]);
            break;
        case "E":
            task = new Events(description, parts[3], parts[4]);
            break;
        default:
            task = new ToDos(description);
        }

        if ("1".equals(status)) {
            task.markAsDone();
        }
        return task;
    }

    private String formatTask(Tasks task) {
        String status = "X".equals(task.getStatusIcon()) ? "1" : "0";

        if (task instanceof ToDos) {
            return "T | " + status + " | " + task.getDescription();
        }
        if (task instanceof Deadlines) {
            Deadlines deadline = (Deadlines) task;
            return "D | " + status + " | " + task.getDescription() + " | " + deadline.getBy();
        }
        if (task instanceof Events) {
            Events event = (Events) task;
            return "E | " + status + " | " + task.getDescription()
                    + " | " + event.getStart() + " | " + event.getEnd();
        }
        return "T | " + status + " | " + task.getDescription();
    }
}

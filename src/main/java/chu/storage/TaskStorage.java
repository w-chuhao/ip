package chu.storage;

import chu.tasklist.TaskList;
import chu.tasks.Deadlines;
import chu.tasks.Events;
import chu.tasks.Tasks;
import chu.tasks.ToDos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    private static final Path DATA_FILE_PATH = Paths.get("data", "chu.txt");
    private static final DateTimeFormatter STORAGE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void initStorage() {
        try {
            Path parentDirectory = DATA_FILE_PATH.getParent();
            if (parentDirectory != null) {
                Files.createDirectories(parentDirectory);
            }
            if (!Files.exists(DATA_FILE_PATH)) {
                Files.createFile(DATA_FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Unable to initialize storage file.");
        }
    }

    public TaskList loadTasks() {
        ArrayList<Tasks> task = new ArrayList<>();
        try (Scanner scanner = new Scanner(DATA_FILE_PATH.toFile())) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    task.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to load tasks from storage.");
        }
        return new TaskList(task);
    }

    public void saveTasks(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE_PATH.toFile())) {
            for (Tasks currentTask : taskList.list()) {
                fileWriter.write(formatTask(currentTask) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Unable to save tasks to storage.");
        }
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
            task = new Deadlines(description, parseStoredDateTime(parts[3]));
            break;
        case "E":
            task = new Events(description, parseStoredDateTime(parts[3]), parseStoredDateTime(parts[4]));
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
            return "D | " + status + " | " + task.getDescription()
                    + " | " + deadline.getBy().format(STORAGE_DATE_TIME_FORMAT);
        }
        if (task instanceof Events) {
            Events event = (Events) task;
            return "E | " + status + " | " + task.getDescription()
                    + " | " + event.getStart().format(STORAGE_DATE_TIME_FORMAT)
                    + " | " + event.getEnd().format(STORAGE_DATE_TIME_FORMAT);
        }
        return "T | " + status + " | " + task.getDescription();
    }

    private LocalDateTime parseStoredDateTime(String raw) {
        return LocalDateTime.parse(raw, STORAGE_DATE_TIME_FORMAT);
    }
}

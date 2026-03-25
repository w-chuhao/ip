package chu.tasklist;

import chu.errorhandler.ChuException;
import chu.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws ChuException {
        return tasks.remove(index);
    }

    public Task mark(int index) throws ChuException {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    public Task unmark(int index) throws ChuException {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> list() {
        return new ArrayList<>(tasks);
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}

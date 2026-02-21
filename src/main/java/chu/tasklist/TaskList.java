package chu.tasklist;

import chu.errorHandler.ChuExceptions;
import chu.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Tasks> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public void add(Tasks task) {
        tasks.add(task);
    }

    public Tasks delete(int index) throws ChuExceptions {
        return tasks.remove(index);
    }

    public Tasks mark(int index) throws ChuExceptions {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    public Tasks unmark(int index) throws ChuExceptions {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Tasks> list() {
        return new ArrayList<>(tasks);
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}

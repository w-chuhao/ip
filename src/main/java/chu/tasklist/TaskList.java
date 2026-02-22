package chu.tasklist;

import chu.errorHandler.ChuExceptions;
import chu.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the list of tasks in memory.
 */
public class TaskList {
    private final ArrayList<Tasks> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from an existing task collection.
     *
     * @param tasks Existing tasks to initialize with.
     */
    public TaskList(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Tasks task) {
        tasks.add(task);
    }

    /**
     * Deletes and returns a task at the given index.
     *
     * @param index Zero-based index of the task.
     * @return The deleted task.
     * @throws ChuExceptions Unused by implementation but declared by callers.
     */
    public Tasks delete(int index) throws ChuExceptions {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done and returns it.
     *
     * @param index Zero-based index of the task.
     * @return The updated task.
     * @throws ChuExceptions Unused by implementation but declared by callers.
     */
    public Tasks mark(int index) throws ChuExceptions {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmarks a task as done and returns it.
     *
     * @param index Zero-based index of the task.
     * @return The updated task.
     * @throws ChuExceptions Unused by implementation but declared by callers.
     */
    public Tasks unmark(int index) throws ChuExceptions {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks.
     *
     * @return Current task count.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a defensive copy of all tasks.
     *
     * @return A list copy of tasks.
     */
    public List<Tasks> list() {
        return new ArrayList<>(tasks);
    }

    /**
     * Prints tasks with one-based numbering.
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Returns a task by index.
     *
     * @param index Zero-based task index.
     * @return Task at the given index.
     */
    public Tasks getTask(int index){
        return tasks.get(index);
    }

}

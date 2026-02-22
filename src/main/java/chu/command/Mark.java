package chu.command;

import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;
import chu.storage.TaskStorage;
import chu.tasklist.TaskList;
import chu.tasks.Tasks;

public class Mark implements command {
    private final String[] sentence;

    public Mark(String[] sentence) {
        this.sentence = sentence;
    }

    @Override
    public void execute(TaskList taskList, TaskStorage storage) throws ChuExceptions {
        int markIndex = ErrorHandler.handleIndex(sentence, taskList.size());
        Tasks markedTask = taskList.mark(markIndex);
        storage.saveTasks(taskList);
        System.out.println("Nice! I've marked this tasks as done:");
        System.out.println(markedTask);
    }
}

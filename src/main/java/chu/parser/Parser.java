package chu.parser;

import chu.command.ByeCommand;
import chu.command.Command;
import chu.command.DeadlineCommand;
import chu.command.DeleteCommand;
import chu.command.EventCommand;
import chu.command.FindCommand;
import chu.command.ListCommand;
import chu.command.MarkCommand;
import chu.command.ToDoCommand;
import chu.command.UnmarkCommand;
import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;

public class Parser {
    public Command parse(String input) throws ChuExceptions {
        ErrorHandler.handleEmpty(input);
        String line = input.trim().toLowerCase();
        String[] sentence = line.split(" ");
        String command = sentence[0];

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new ToDoCommand(line);
        case "deadline":
            return new DeadlineCommand(line);
        case "event":
            return new EventCommand(line);
        case "delete":
            return new DeleteCommand(sentence);
        case "mark":
            return new MarkCommand(sentence);
        case "unmark":
            return new UnmarkCommand(sentence);
        case "find":
            return new FindCommand(line);
        default:
            throw new ChuExceptions("Invalid command. Valid commands: bye, list, todo, deadline, event, delete, mark, unmark, find.");
        }
    }
}

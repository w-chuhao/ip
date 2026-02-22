package chu.parser;

import chu.command.Bye;
import chu.command.command;
import chu.command.Deadline;
import chu.command.Delete;
import chu.command.Event;
import chu.command.List;
import chu.command.Mark;
import chu.command.ToDo;
import chu.command.Unmark;
import chu.errorHandler.ChuExceptions;
import chu.errorHandler.ErrorHandler;

public class Parser {
    public command parse(String input) throws ChuExceptions {
        ErrorHandler.handleEmpty(input);
        String line = input.trim().toLowerCase();
        String[] sentence = line.split(" ");
        String command = sentence[0];

        switch (command) {
        case "bye":
            return new Bye();
        case "list":
            return new List();
        case "todo":
            return new ToDo(line);
        case "deadline":
            return new Deadline(line);
        case "event":
            return new Event(line);
        case "delete":
            return new Delete(sentence);
        case "mark":
            return new Mark(sentence);
        case "unmark":
            return new Unmark(sentence);
        default:
            throw new ChuExceptions("Invalid command. Valid commands: bye, list, todo, deadline, event, mark, unmark.");
        }
    }
}

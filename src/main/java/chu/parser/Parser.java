package chu.parser;

import chu.command.bye;
import chu.command.command;
import chu.command.deadline;
import chu.command.delete;
import chu.command.event;
import chu.command.list;
import chu.command.mark;
import chu.command.todo;
import chu.command.unmark;
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
            return new bye();
        case "list":
            return new list();
        case "todo":
            return new todo(line);
        case "deadline":
            return new deadline(line);
        case "event":
            return new event(line);
        case "delete":
            return new delete(sentence);
        case "mark":
            return new mark(sentence);
        case "unmark":
            return new unmark(sentence);
        default:
            throw new ChuExceptions("Invalid command. Valid commands: bye, list, todo, deadline, event, mark, unmark.");
        }
    }
}

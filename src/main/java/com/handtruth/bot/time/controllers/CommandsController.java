package com.handtruth.bot.time.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.telegram.telegrambots.meta.api.objects.User;

import com.handtruth.bot.time.services.DtaskService;
import com.handtruth.bot.time.utils.Action;
import com.handtruth.bot.time.utils.Command;

import static com.handtruth.bot.time.utils.Command.*;

public class CommandsController {
    private DtaskService dtaskService = DtaskService.getINSTANCE();

    public boolean isCommand(String text) {
        for (Command command : values()) {
            if (command.getName().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public Action execute(String cmnd, User user) {
        Command command = valueOf(cmnd);
        switch (command) {
            case start:
                return new Action(Action.Act.Message, "new String");
            case info:
                return new Action(Action.Act.Message, "new String");
            case help:
                return new Action(Action.Act.Message, "new String");
            case completeD:
                Calendar c = new GregorianCalendar();
                c.get(Calendar.DAY_OF_WEEK);
                return new Action(Action.Act.Message, "new String");
            case failedD:
                return new Action(Action.Act.Message, "new String");
            case completeM:
                return new Action(Action.Act.Message, "new String");
            case failedM:
                return new Action(Action.Act.Message, "new String");
            case completeY:
                return new Action(Action.Act.Message, "new String");
            case failedY:
                return new Action(Action.Act.Message, "new String");
            case statistic:
                return new Action(Action.Act.Message, "new String");
            default:
                return new Action(Action.Act.Message, "Команды не существует");
        }
    }

}

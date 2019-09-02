package com.handtruth.bot.time.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.services.ChatsService;
import com.handtruth.bot.time.services.DtaskService;
import com.handtruth.bot.time.utils.Action;
import com.handtruth.bot.time.utils.Command;

import static com.handtruth.bot.time.utils.Command.*;

public class CommandsController {
    private DtaskService dtaskService;
    private ChatsService chatsService;

    private static final CommandsController INSTANCE = new CommandsController();

    private CommandsController() {
        dtaskService = DtaskService.getInstance();
        chatsService = ChatsService.getInstance();
    }

    public static CommandsController getInstance() {
        return INSTANCE;
    }

    private InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();

    public boolean isCommand(String text) {
        for (Command command : values()) {
            if (command.getName().equals(text)) {
                return true;
            }
        }
        return false;
    }

    private int curfew = 22;

    private String[] startAnswers = new String[] {"Привет! Меня зовут Time Bot!Я буду вашим помощником!",
            "Чат уже сущетвует", "Не балуйся!", "Руки оторву!!!"};
    private String infoAnswer = "";
    private String helpAnswer = "Вы можете использовать следующие команды: \n" +
            "/start - регистрирует чат \n" +
            "/disband - стирает чат \n" +
            "/info - информация о боте\n" +
            "/completeD - ежедневное задание выполнено!\n" +
            "/failedD - ежедневное задание провално!\n" +
            "/statistic - текущая статистика";

    private String[] completeDAnswer = new String[] {
            "Выберите день!", "Не вовремя! Вы можете завершать задания по будням с 22:00 до 00:00 или в выходные дни!"};

    private String[] failedDAnswer = new String[] {"Не фортануло не свезло", "Ну чтож, придется попотеть...",
            "Слабак!", "Ну ничего, малыш, все образуется!",
            "Эту команду можно отправляьб только по будням с 22:00 до 00:00 или в выходные дни!"};

    public Action execute(Message msg) {
        dropKeys();
        if (!isCommand(msg.getText())) {
            return new Action(Action.Act.No, null);
        }
        Command command = valueOf(msg.getText());
        Calendar c = new GregorianCalendar();
        int day = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        switch (command) {
            case start:
                if (chatsService.hasChat(msg.getChatId())) {
                    int i = new Random().nextInt(3) + 1;
                    return new Action(Action.Act.Message, startAnswers[i]);
                } else {
                    return new Action(Action.Act.Message, startAnswers[0]);
                }
            case info:
                return new Action(Action.Act.Message, "new String");
            case help:
                return new Action(Action.Act.Message, helpAnswer);
            case completeD:
                if (hour >= curfew || (day == 1 || day == 7)) {
                    List<String> buttons = new ArrayList<>();
                    Dtask dtask = dtaskService.find(msg.getFrom());
                    if (!dtask.isMonday()) buttons.add("Понедельник");
                    if (!dtask.isTuesday() && day != 2) buttons.add("Вторник");
                    if (!dtask.isWednesday() && !(day >=2 && day <= 3)) buttons.add("Среда");
                    if (!dtask.isThursday() && !(day >=2 && day <= 4)) buttons.add("Четверг");
                    if (!dtask.isFriday() && !(day >=2 && day <= 5)) buttons.add("Пятница");
                    setKeys(buttons);
                    return new Action(Action.Act.Message, completeDAnswer[0]);
                }
                return new Action(Action.Act.Message, completeDAnswer[1]);
            case failedD:
                if (hour >= curfew || (day == 1 || day == 7)) {
                    int i = new Random().nextInt(4);
                    return new Action(Action.Act.Message, failedDAnswer[i]);
                } else {
                    return new Action(Action.Act.Message, failedDAnswer[4]);
                }
            case completeM:
                return new Action(Action.Act.Message, "Временно недоступно!");
            case failedM:
                return new Action(Action.Act.Message, "Временно недоступно!");
            case completeY:
                return new Action(Action.Act.Message, "Временно недоступно!");
            case failedY:
                return new Action(Action.Act.Message, "Временно недоступно!");
            case statistic:
                return new Action(Action.Act.Message, "Временно недоступно!");
            default:
                return new Action(Action.Act.Message, "Команды не существует");
        }
    }


    public Action callback(CallbackQuery callbackQuery) {
        dropKeys();
        String day = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        Dtask dtask = dtaskService.find(user);
        switch (day) {
            case "Понедельник":
                dtask.setMonday(true);
                break;
            case "Вторник":
                dtask.setTuesday(true);
                break;
            case "Среда":
                dtask.setWednesday(true);
                break;
            case "Четверг":
                dtask.setThursday(true);
                break;
            default:
                dtask.setFriday(true);
                break;
        }
        dtaskService.update(user, dtask);
        return new Action(Action.Act.Message, "Получено!");
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

    private void setKeys(List<String> list) {
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        for (String button: list) {
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(button).setCallbackData(button));
        }
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
    }

    private void dropKeys() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
    }
}

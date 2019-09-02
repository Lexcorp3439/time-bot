package com.handtruth.bot.time.inst;

import java.util.Arrays;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import com.handtruth.bot.time.controllers.CommandsController;
import com.handtruth.bot.time.utils.Action;

public class TimeBot extends TelegramLongPollingBot {
    public static volatile TimeBot timeBot = new TimeBot();

    public static final String BOT_NAME = "TimeBot";
    private static final String BOT_USERNAME = "handtruth_time_bot";
    private static final String BOT_TOKEN = "952136317:AAFqTrVBBMJn_MrCsiANmpFagvUYuKtVU_M";
    private static final String TAG = "HandBot message";
    private static final String VERSION = "1.0.1";

    private static long chatID = 0;

    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            chatID = message.getChatId();
            if (message.hasText()) {
                Action action = CommandsController.getInstance().execute(message);
                if (action.act == Action.Act.Message) {
                    sendMsg(action.messages);
                }
            }
        }else if (update.hasCallbackQuery()) {
            Action action = CommandsController.getInstance().callback(update.getCallbackQuery());
            if (action.act == Action.Act.Message) {
                sendMsg(action.messages);
            }
        }
    }

    public synchronized void sendMsg(String text) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setReplyMarkup(CommandsController.getInstance().getInlineKeyboardMarkup());
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(text);
        System.out.println(chatID);
        System.out.println(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            BotLogger.error("Could not send message", TAG, e);
        }
    }

    private void sendPhoto(String[] ids, long chat_id) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chat_id);
        System.out.println(Arrays.toString(ids));
        for (int i = 0; i < ids.length - 1; i++) {
            sendPhoto.setPhoto(ids[i]);

            if (i == ids.length - 2) {
                sendPhoto.setCaption(ids[ids.length - 1]);
            }
            try {
                execute(sendPhoto); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

}

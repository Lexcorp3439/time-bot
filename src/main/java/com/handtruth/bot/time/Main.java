package com.handtruth.bot.time;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.handtruth.bot.time.timers.EveningTimerTask;
import com.handtruth.bot.time.timers.MorningTimerTask;
import com.handtruth.bot.time.utils.Properties;

public class Main {
    public static void main(String[] args) {
        Properties.setProxy();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new com.handtruth.bot.time.inst.TimeBot());
            System.out.println("Bot start!!!");

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        setMorningTimer();
        setEveningTimer();
    }

    static void setEveningTimer() {
        Calendar c = new GregorianCalendar();

        Calendar c1 = new GregorianCalendar();
        if (c.get(Calendar.HOUR_OF_DAY) > 22) {
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        c1.set(Calendar.HOUR_OF_DAY, 22);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        Timer timer1 = new Timer();
        timer1.schedule(new EveningTimerTask(), c1.getTime(), 86400000);
    }

    static void setMorningTimer() {
        Calendar c = new GregorianCalendar();

        Calendar c2 = new GregorianCalendar();
        if (c.get(Calendar.HOUR_OF_DAY) > 8) {
            c2.add(Calendar.DAY_OF_MONTH, 1);
        }
        c2.set(Calendar.HOUR_OF_DAY, 8);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);

        Timer timer2 = new Timer();
        timer2.schedule(new MorningTimerTask(), c2.getTime(), 86400000);
    }


}

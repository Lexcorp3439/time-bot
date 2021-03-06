package com.handtruth.bot.time.timers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

import com.handtruth.bot.time.inst.TimeBot;

public class EveningTimerTask extends TimerTask {

    public void run() {
        Calendar calendar = new GregorianCalendar();
        if (calendar.get(Calendar.DAY_OF_WEEK) < 7 && calendar.get(Calendar.DAY_OF_WEEK) > 1) {
            TimeBot.timeBot.sendMsg("Пришло время Подвести итоги дня!");
            TimeBot.timeBot.sendMsg("Отправляй команду /completeD если все готово, " +
                    "или команду /failedD, если не успел доделать все задания из плана!");
//            TimeBot.timeBot.sendMsg("Не забудьте составить план на завтра!");
        } else {
            TimeBot.timeBot.sendMsg("Пришло время для подведения итогов недели!");
            TimeBot.timeBot.sendMsg("Постарайтесь на следующей неделе еще больше! Удачи!");
        }
//        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//            TimeBot.timeBot.sendMsg("Пришло время для подведения итогов недели!");
//            List<Dtask> dtasks = DtaskService.getINSTANCE().all();
//            for (Dtask row: dtasks) {
//                TimeBot.timeBot.sendMsg(row.getName() + " выполнил за неделю " + row.getCount() + " ежедневных заданий!");
//            }
//            TimeBot.timeBot.sendMsg("Постарайтесь на следующей неделе еще больше! Удачи!");
//        }
    }
}

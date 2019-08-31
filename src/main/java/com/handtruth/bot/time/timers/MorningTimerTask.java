package com.handtruth.bot.time.timers;

import java.util.TimerTask;

import com.handtruth.bot.time.inst.TimeBot;

public class MorningTimerTask extends TimerTask {
    public void run() {
        TimeBot.timeBot.sendMsg("Доброе утро! Пришло время очень хорошо потрудиться, чтобы выполнить весь дневной план!");
    }
}

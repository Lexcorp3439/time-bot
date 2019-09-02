package com.handtruth.bot.time.utils;

public enum Command {
    start("/start"),
    info("/info"),
    help("/help"),
    completeD("/completeD"),
    failedD("/failedD"),
    completeM("/completeM"),
    failedM("/failedM"),
    completeY("/completeY"),
    failedY("/failedY"),
    statistic("/statistic"),
    disband("/disband");

    private String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.handtruth.bot.time.database;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.entities.Users;
import com.handtruth.bot.time.services.DtaskService;

public class DtaskServiceTest {

    @BeforeClass
    public static void createTest() {
        Dtask dtask1 = new Dtask(0, new Users(234324534,"Alex"));
        Dtask dtask2 = new Dtask(0, new Users(23154235,"Vlad"));
        Dtask dtask3 = new Dtask(0, new Users(343245,"Moris"));
        Dtask dtask4 = new Dtask(0, new Users(239834,"Egor"));
        Dtask dtask5 = new Dtask(0, new Users(99987878,"Azat"));
        DtaskService.getINSTANCE().createDtask(dtask1);
        DtaskService.getINSTANCE().createDtask(dtask2);
        DtaskService.getINSTANCE().createDtask(dtask3);
        DtaskService.getINSTANCE().createDtask(dtask4);
        DtaskService.getINSTANCE().createDtask(dtask5);
    }

    @Test
    public void dropTest() {
        DtaskService.getINSTANCE().dropDtaskByUserId(234324534);
    }

    @Test
    public void findAllTest() {
        List<Dtask> list = DtaskService.getINSTANCE().all();
        for (Dtask dtask: list) {
            System.out.println(dtask.getUser().getName());
        }
    }

    @AfterClass
    public static void deleteAll() {
        DtaskService.getINSTANCE().dropAll();
    }
}

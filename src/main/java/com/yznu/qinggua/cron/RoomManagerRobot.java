package com.yznu.qinggua.cron;

import com.yznu.qinggua.service.IScheduleService;
import com.yznu.qinggua.utils.DDRobot;
import com.yznu.qinggua.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RoomManagerRobot {
    @Autowired
    DDRobot ddRobot;

    @Autowired
    IScheduleService iScheduleService;

    @Scheduled(cron = Global.CROONERS2ROBOT)
    public void autoPlay() {
        // System.out.println("Hello World!");
    }

}

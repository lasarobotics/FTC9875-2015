package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.monkeyc.MonkeyData;
import com.lasarobotics.library.monkeyc.MonkeyDo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCRead {
    private Logic logic;
    private OpMode context;
    private MonkeyDo monkeyDo;

    public MonkeyCRead(OpMode context, boolean twoControllers) {
        this.context = context;
        logic = new Logic(context, twoControllers);
    }

    public void init() {
        monkeyDo = new MonkeyDo("default.txt");
        logic.init();
    }

    public void start() {
        monkeyDo.onStart();
    }

    public void loop() {
        MonkeyData monkeyData = monkeyDo.getNextCommand();
        if(monkeyData != null && monkeyData.hasUpdate()) {
            monkeyData = monkeyDo.getNextCommand();
            logic.c1 = monkeyData.updateControllerOne(logic.c1);
            logic.c2 = monkeyData.updateControllerOne(logic.c2);
            logic.loop();
        }
    }

    public void stop() {
        logic.stop();
    }
}

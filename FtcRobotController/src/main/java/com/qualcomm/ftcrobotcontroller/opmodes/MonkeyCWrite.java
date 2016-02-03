package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.Controller;
import com.lasarobotics.library.monkeyc.MonkeyC;
import com.lasarobotics.library.options.OptionMenu;
import com.lasarobotics.library.options.TextCategory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCWrite {
    private Logic logic;
    private OpMode context;
    private MonkeyC monkeyC = new MonkeyC();

    Controller c1, c2;

    public MonkeyCWrite(OpMode context, boolean twoControllers) {
        this.context = context;
        logic = new Logic(context, twoControllers);
    }

    public void init() {
        logic.init();
    }

    public void loop() {
        logic.updateControllers();
        c1 = logic.c1;
        c2 = logic.c2;
        monkeyC.add(c1, c2);
        logic.loop();
    }

    public void stop() {
        logic.stop();

        monkeyC.end();
        monkeyC.write("default.txt", true);
    }
}

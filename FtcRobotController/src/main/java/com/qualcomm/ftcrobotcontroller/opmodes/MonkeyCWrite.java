package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.Controller;
import com.lasarobotics.library.monkeyc.MonkeyC;
import com.lasarobotics.library.options.OptionMenu;
import com.lasarobotics.library.options.TextCategory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCWrite {
    private Logic logic;
    private OpMode context;
    private boolean twoControllers;
    private MonkeyC monkeyC = new MonkeyC();

    Controller c1, c2;

    private OptionMenu fileChooser;
    private TextCategory fileName;

    public MonkeyCWrite(OpMode context, boolean twoControllers) {
        this.context = context;
        this.twoControllers = twoControllers;
    }

    public void init() {
        OptionMenu.Builder builder = new OptionMenu.Builder(context.hardwareMap.appContext);
        fileName = new TextCategory("filename");
        fileChooser = builder.create();
        fileChooser.show();

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
        monkeyC.write(fileName.getResult(), true);
    }
}

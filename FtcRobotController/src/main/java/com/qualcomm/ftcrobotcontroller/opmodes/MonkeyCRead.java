package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.monkeyc.MonkeyData;
import com.lasarobotics.library.monkeyc.MonkeyDo;
import com.lasarobotics.library.options.OptionMenu;
import com.lasarobotics.library.options.TextCategory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCRead {
    private Logic logic;
    private OpMode context;
    private MonkeyDo monkeyDo;

    private OptionMenu menu;
    private TextCategory filename;

    public MonkeyCRead(OpMode context, boolean twoControllers) {
        this.context = context;
        logic = new Logic(context, twoControllers);
    }

    public void init() {
        OptionMenu.Builder builder = new OptionMenu.Builder(context.hardwareMap.appContext);
        filename = new TextCategory("program name");
        builder.addCategory(filename);
        menu = builder.create();
        menu.show();

        logic.init();
    }

    public void start() {
        monkeyDo = new MonkeyDo("monkeyc_" + filename.getResult() + ".txt");
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

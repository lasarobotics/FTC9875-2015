package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TeleOp2 extends OpMode {
    Logic logic = new Logic(this, true);

    public void init() {
        logic.init();
    }

    public void start() { logic.start(); }

    public void loop() {
        logic.updateControllers();
        logic.loop();
    }

    public void stop() { logic.stop(); }
}

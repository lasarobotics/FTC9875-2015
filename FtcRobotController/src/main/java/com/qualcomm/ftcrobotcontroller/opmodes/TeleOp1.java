package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by will on 1/9/16.
 */

public class TeleOp1 extends OpMode {
    Logic logic = new Logic(this, false);

    public void init() {
        logic.init();
    }
    public void loop() {
        logic.loop();
    }
    public void stop() {
        logic.stop();
    }
}

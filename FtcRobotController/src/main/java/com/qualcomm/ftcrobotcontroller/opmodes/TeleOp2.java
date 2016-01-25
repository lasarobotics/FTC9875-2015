package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by will on 1/9/16.
 */

public class TeleOp2 extends OpMode {
    Logic logic = new Logic(this, true);

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

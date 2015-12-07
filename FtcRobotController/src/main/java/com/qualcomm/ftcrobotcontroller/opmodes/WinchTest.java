package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by will on 12/2/15.
 */
public class WinchTest extends OpMode {
    DcMotor winch_motor;

    public void init() {
        winch_motor = hardwareMap.dcMotor.get("w");
    }
    public void loop() {
        winch_motor.setPower(1);
    }
    public void stop() {
        winch_motor.setPower(0);
    }
}

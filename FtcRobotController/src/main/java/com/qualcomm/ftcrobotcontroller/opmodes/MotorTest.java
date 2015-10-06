package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.Controller;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by will on 10/6/15.
 */

public class MotorTest extends OpMode {
    Controller controller = new Controller();
    DcMotor left_motor, right_motor;

    public void init() {
        left_motor = hardwareMap.dcMotor.get("left");
        right_motor = hardwareMap.dcMotor.get("right");
    }
    public void loop() {
        controller.update(gamepad1);
    }
    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
    }
}

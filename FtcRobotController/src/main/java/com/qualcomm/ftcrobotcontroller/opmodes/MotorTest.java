package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by will on 10/6/15.
 * Touched by Arnav
 */

public class MotorTest extends OpMode {
    Controller controller = new Controller();
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;

    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("left-back");
        left_front_motor = hardwareMap.dcMotor.get("left-front");
        right_back_motor = hardwareMap.dcMotor.get("right-back");
        right_front_motor = hardwareMap.dcMotor.get("right-front");
    }
    public void loop() {
        left_front_motor.setPower(-gamepad1.left_stick_y);
        left_back_motor.setPower(-gamepad1.left_stick_y);
        right_front_motor.setPower(gamepad1.right_stick_y);
        right_back_motor.setPower(gamepad1.right_stick_y);
    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
    }
}
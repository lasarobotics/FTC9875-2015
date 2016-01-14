package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.util.SmartMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by will on 1/9/16.
 */

public class TeleOp1 extends OpMode {
    SmartMotor left_motor, right_motor, pulley_motor, winch_motor;
    double speed_scale = 1;

    public void init() {
        left_motor = new SmartMotor(this, "left");
        right_motor = new SmartMotor(this, "right");
        pulley_motor = new SmartMotor(this, "pulley");
        winch_motor = new SmartMotor(this, "winch");
    }
    public void loop() {
        speed_scale = gamepad1.a ? 0.5 : (gamepad1.b ? 0.25 : 1);
        if(gamepad1.left_bumper) {
            left_motor.setPower(-speed_scale);
            right_motor.setPower(-speed_scale);
        } else if(gamepad1.right_bumper) {
            left_motor.setPower(0.5*speed_scale);
            right_motor.setPower(0.5*speed_scale);
        } else {
            left_motor.setPower(gamepad1.left_stick_y * speed_scale);
            right_motor.setPower(-gamepad1.right_stick_y * speed_scale);
        }

        if(gamepad1.dpad_up) {
            pulley_motor.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            pulley_motor.setPower(-0.5);
        } else {
            pulley_motor.setPower(0);
        }

        if(gamepad1.dpad_left) {
            winch_motor.setPower(0.5);
        } else if(gamepad1.dpad_right) {
            winch_motor.setPower(-0.5);
        } else {
            winch_motor.setPower(0);
        }
    }
    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
        pulley_motor.setPower(0);
        winch_motor.setPower(0);
    }
}

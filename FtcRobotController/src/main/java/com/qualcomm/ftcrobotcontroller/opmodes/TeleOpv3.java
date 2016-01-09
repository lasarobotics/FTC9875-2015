package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by will on 1/9/16.
 */

public class TeleOpv3 extends OpMode {
    DcMotor left_motor, right_motor;
    double speed_scale = 1;

    public void init() {
        left_motor = hardwareMap.dcMotor.get("l");
        right_motor = hardwareMap.dcMotor.get("r");
    }
    public void loop() {
        speed_scale = gamepad1.a ? 0.5 : (gamepad1.b ? 0.25 : 1);
        if(gamepad1.left_bumper) {
            left_motor.setPower(speed_scale);
            right_motor.setPower(speed_scale);
        } else if(gamepad1.right_bumper) {
            left_motor.setPower(-0.5*speed_scale);
            right_motor.setPower(-0.5*speed_scale);
        } else {
            left_motor.setPower(-gamepad1.left_stick_y * speed_scale);
            right_motor.setPower(gamepad1.right_stick_y * speed_scale);
        }
    }
    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
    }
}

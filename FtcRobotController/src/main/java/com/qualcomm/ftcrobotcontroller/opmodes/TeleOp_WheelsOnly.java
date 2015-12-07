package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by will on 10/6/15.
 */

public class TeleOp_WheelsOnly extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;
    double speed_scale = 1;

    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("lb");
        left_front_motor = hardwareMap.dcMotor.get("lf");
        right_back_motor = hardwareMap.dcMotor.get("rb");
        right_front_motor = hardwareMap.dcMotor.get("rf");
    }
    public void loop() {
        speed_scale = gamepad1.a ? 0.5 : (gamepad1.b ? 0.25 : 1);
        if(gamepad1.left_bumper) {
            left_front_motor.setPower(speed_scale);
            left_back_motor.setPower(speed_scale);
            right_front_motor.setPower(speed_scale);
            right_back_motor.setPower(speed_scale);
        } else if(gamepad1.right_bumper) {
            left_front_motor.setPower(-0.5*speed_scale);
            left_back_motor.setPower(-0.5*speed_scale);
            right_front_motor.setPower(-0.5*speed_scale);
            right_back_motor.setPower(-0.5*speed_scale);
        } else {
            left_front_motor.setPower(-gamepad1.left_stick_y * speed_scale);
            left_back_motor.setPower(-gamepad1.left_stick_y * speed_scale);
            right_front_motor.setPower(gamepad1.right_stick_y * speed_scale);
            right_back_motor.setPower(gamepad1.right_stick_y * speed_scale);
        }
    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
    }
}

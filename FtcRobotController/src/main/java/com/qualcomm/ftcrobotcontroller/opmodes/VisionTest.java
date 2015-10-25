package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.Controller;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by will on 10/19/15.
 */

public class VisionTest extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;
    Servo climber_arm_servo;

    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("left-back");
        left_front_motor = hardwareMap.dcMotor.get("left-front");
        right_back_motor = hardwareMap.dcMotor.get("right-back");
        right_front_motor = hardwareMap.dcMotor.get("right-front");
        climber_arm_servo = hardwareMap.servo.get("climber-arm");
    }
    public void loop() {
    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
    }
}

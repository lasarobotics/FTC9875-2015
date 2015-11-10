package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Random;

/**
 * Created by will on 11/7/15.
 */
public class Autonomous extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;
    Random rand = new Random();
    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("lb");
        left_front_motor = hardwareMap.dcMotor.get("lf");
        right_back_motor = hardwareMap.dcMotor.get("rb");
        right_front_motor = hardwareMap.dcMotor.get("rf");
    }
    public void loop() {
        left_front_motor.setPower(1);
        left_back_motor.setPower(1);
        right_front_motor.setPower(1);
        right_back_motor.setPower(1);
    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
    }
}

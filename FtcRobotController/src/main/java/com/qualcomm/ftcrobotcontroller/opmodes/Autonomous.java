package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by will on 11/7/15.
 * Cleaned by Rishi on 11/12/15.
 */
public class Autonomous extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;
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

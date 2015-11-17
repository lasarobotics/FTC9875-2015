package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.drive.Mecanum;
import com.lasarobotics.library.drive.Tank;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Ethan on 11/2/15.
 */


public class tele extends OpMode {

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor lift;
    DcMotor infeed;

    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("lf");
        rightFront = hardwareMap.dcMotor.get("rf");
        leftBack = hardwareMap.dcMotor.get("lb");
        rightBack = hardwareMap.dcMotor.get("rb");
        lift = hardwareMap.dcMotor.get("w");
        infeed = hardwareMap.dcMotor.get("b");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        Tank.Motor4(leftFront, rightFront, leftBack, rightBack, gamepad1.right_stick_y, gamepad1.left_stick_y);

        //lift
        if(gamepad1.right_bumper){
            lift.setPower(1);
        } else if(gamepad1.left_bumper){
            lift.setPower(-1);
        } else {
            lift.setPower(0);
        }

        if(gamepad1.a){
            infeed.setPower(1);
        } else if(gamepad1.x){
            infeed.setPower(-1);
        } else {
            infeed.setPower(0);
        }

    }
}

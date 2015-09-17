package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.drive.Mecanum;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by ashwi_000 on 9/17/2015.
 */
public class Test extends OpMode {

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;

    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
    }

    @Override
    public void loop() {
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        Mecanum.Arcade(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, leftFront, rightFront, leftBack, rightBack);
    }
}

package com.qualcomm.ftcrobotcontroller.opmodes;

import android.widget.Button;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;
import com.qualcomm.ftccommon.FtcEventLoop;
import com.qualcomm.ftccommon.FtcEventLoopHandler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;

import java.util.HashMap;

/**
 * Created by will on 10/6/15.
 * Touched by Arnav
 */

public class MotorTest extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor;
    double speed_scale = 1;
    HashMap<String, Long> time_since_last_pressed;
    HashMap<String, Long> time_last_pressed;

    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("lb");
        left_front_motor = hardwareMap.dcMotor.get("lf");
        right_back_motor = hardwareMap.dcMotor.get("rb");
        right_front_motor = hardwareMap.dcMotor.get("rf");

        time_since_last_pressed.put("a", new Long(0));
        time_last_pressed.put("a", new Long(0));
    }
    public void loop() {
        long time = System.currentTimeMillis();
        time_since_last_pressed.put("a", time - time_last_pressed.get("a"));

        if(gamepad1.a == true && time_since_last_pressed.get("a") > 500) {
            speed_scale = speed_scale == 1 ? 0.5 : 1;
            time_last_pressed.put("a", time);
        }

        left_front_motor.setPower(-gamepad1.left_stick_y*speed_scale);
        left_back_motor.setPower(-gamepad1.left_stick_y*speed_scale);
        right_front_motor.setPower(gamepad1.right_stick_y*speed_scale);
        right_back_motor.setPower(gamepad1.right_stick_y*speed_scale);


    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
    }
}

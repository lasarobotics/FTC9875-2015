package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Logic {
    OpMode context;
    boolean twoControllers;

    Gamepad g1, g2;

    MotorWrapper left_motor, right_motor, pulley_motor, winch_motor;
    double speed_scale1, speed_scale2;

    public Logic(OpMode context, boolean twoControllers) {
        this.context = context;
        this.twoControllers = twoControllers;
    }

    public void init() {
        left_motor = new MotorWrapper(context, "left");
        right_motor = new MotorWrapper(context, "right");

        g1 = context.gamepad1;

        if(twoControllers) {
            g2 = context.gamepad2;
            pulley_motor = new MotorWrapper(context, "pulley");
            winch_motor = new MotorWrapper(context, "winch");
        } else {
            g2 = context.gamepad1;
        }
    }

    public void loop() {
        speed_scale1 = g1.a ? 0.5 : (g1.b ? 0.25 : 1);
        speed_scale2 = g2.a ? 0.5 : (g2.b ? 0.25 : 1);

        if(g1.left_bumper) {
            left_motor.setPower(speed_scale1);
            right_motor.setPower(speed_scale1);
        } else if(g1.right_bumper) {
            left_motor.setPower(-speed_scale1);
            right_motor.setPower(-speed_scale1);
        } else {
            left_motor.setPower(g1.left_stick_y * speed_scale1);
            right_motor.setPower(-g1.right_stick_y * speed_scale1);
        }

        pulley_motor.setPower(speed_scale2 * g2.left_stick_y);
        winch_motor.setPower(speed_scale2 * g2.right_stick_y);
    }

    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
        pulley_motor.setPower(0);
        winch_motor.setPower(0);
    }
}

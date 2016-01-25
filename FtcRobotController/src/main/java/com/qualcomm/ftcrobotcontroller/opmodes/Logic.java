package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Logic {
    private OpMode context;
    private boolean twoControllers;
    public Controller c1, c2;

    MotorWrapper left_motor, right_motor, pulley_motor, winch_motor;
    double speed_scale1, speed_scale2;

    public Logic(OpMode context, boolean twoControllers) {
        this.context = context;
        this.twoControllers = twoControllers;
    }

    public void init() {
        left_motor = new MotorWrapper(context, "left");
        right_motor = new MotorWrapper(context, "right");
        pulley_motor = new MotorWrapper(context, "pulley");
        winch_motor = new MotorWrapper(context, "winch");
    }

    public void updateControllers() {
        c1 = new Controller(context.gamepad1);
        c2 = twoControllers ? new Controller(context.gamepad2) : new Controller(context.gamepad1);
    }

    public void loop() {
        speed_scale1 = c1.a == ButtonState.HELD ? 0.5 : (c1.b == ButtonState.HELD ? 0.25 : 1);
        speed_scale2 = c2.a == ButtonState.HELD ? 0.5 : (c2.b == ButtonState.HELD ? 0.25 : 1);

        if(c1.left_bumper == ButtonState.HELD) {
            left_motor.setPower(speed_scale1);
            right_motor.setPower(speed_scale1);
        } else if(c1.right_bumper == ButtonState.HELD) {
            left_motor.setPower(-speed_scale1);
            right_motor.setPower(-speed_scale1);
        } else {
            left_motor.setPower(c1.left_stick_y * speed_scale1);
            right_motor.setPower(-c1.right_stick_y * speed_scale1);
        }

        pulley_motor.setPower(speed_scale2 * c2.left_stick_y);
        winch_motor.setPower(speed_scale2 * c2.right_stick_y);
    }

    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
        pulley_motor.setPower(0);
        winch_motor.setPower(0);
    }
}

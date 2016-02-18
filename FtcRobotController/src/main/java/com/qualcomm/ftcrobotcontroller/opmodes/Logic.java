package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Logic {
    private OpMode context;
    private boolean twoControllers;
    public Controller c1, c2;

    DcMotor left_motor, right_motor, pulley_motor, winch_motor;
    Servo arm;

    double arm_pos = 1;
    double speed_scale1, speed_scale2;
    double deadzone = 0.25;

    double time, prev_time = 0;

    // rotational velocity in rad/s
    double servo_rv = Math.PI;

    public Logic(OpMode context, boolean twoControllers) {
        this.context = context;
        this.twoControllers = twoControllers;
    }

    public void init() {
        left_motor = context.hardwareMap.dcMotor.get("left");
        right_motor = context.hardwareMap.dcMotor.get("right");
        pulley_motor = context.hardwareMap.dcMotor.get("pulley");
        winch_motor = context.hardwareMap.dcMotor.get("winch");
        arm = context.hardwareMap.servo.get("arm");
    }

    public void start() {
        c1 = new Controller(context.gamepad1);
        c2 = new Controller(context.gamepad2);

        context.gamepad1.setJoystickDeadzone(0.25f);
        context.gamepad2.setJoystickDeadzone(0.25f);

        time = 0;
        prev_time = 0;
    }

    public void updateControllers() {
        c1.update(context.gamepad1);
        c2.update(context.gamepad2);
    }

    public double applyDeadzone(double val) {
        return Math.abs(val) > deadzone ? val : 0;
    }

    public void loop() {
        updateTime();
        speed_scale1 = c1.a == ButtonState.HELD ? 0.5 : (c1.b == ButtonState.HELD ? 0.25 : 1);

        if(twoControllers) {
            speed_scale2 = c2.a == ButtonState.HELD ? 0.5 : (c2.b == ButtonState.HELD ? 0.25 : 1);

            if (c1.left_bumper == ButtonState.HELD) {
                left_motor.setPower(speed_scale1);
                right_motor.setPower(speed_scale1);
            } else if (c1.right_bumper == ButtonState.HELD) {
                left_motor.setPower(-speed_scale1);
                right_motor.setPower(-speed_scale1);
            } else {
                left_motor.setPower(applyDeadzone(c1.left_stick_y) * speed_scale1);
                right_motor.setPower(-applyDeadzone(c1.right_stick_y) * speed_scale1);
            }

            if(c2.dpad_up == ButtonState.HELD) {
                arm_pos = updateServo(arm_pos, -servo_rv);
            } else if(c2.dpad_down == ButtonState.HELD) {
                arm_pos = updateServo(arm_pos, servo_rv);
            }

            arm.setPosition(arm_pos);

            pulley_motor.setPower(speed_scale2 * applyDeadzone(c2.left_stick_y));
            winch_motor.setPower(-speed_scale2 * applyDeadzone(c2.right_stick_y));
        } else {
            if(c1.left_bumper == ButtonState.HELD) {
                left_motor.setPower(speed_scale1);
                right_motor.setPower(speed_scale1);
            } else if(c1.right_bumper == ButtonState.HELD) {
                left_motor.setPower(-speed_scale1);
                right_motor.setPower(-speed_scale1);
            } else {
                left_motor.setPower(applyDeadzone(c1.left_stick_y) * speed_scale1);
                right_motor.setPower(-applyDeadzone(c1.right_stick_y) * speed_scale1);
            }

            if(c1.dpad_up == ButtonState.HELD) {
                pulley_motor.setPower(-1);
            } else if(c1.dpad_down == ButtonState.HELD) {
                pulley_motor.setPower(1);
            } else {
                pulley_motor.setPower(0);
            }

            if(c1.dpad_left == ButtonState.HELD) {
                winch_motor.setPower(1);
            } else if(c1.dpad_right == ButtonState.HELD) {
                winch_motor.setPower(-1);
            } else {
                winch_motor.setPower(0);
            }

            if(c2.y == ButtonState.HELD) {
                arm_pos = updateServo(arm_pos, -servo_rv);
            } else if(c2.x  == ButtonState.HELD) {
                arm_pos = updateServo(arm_pos, servo_rv);
            }

            arm.setPosition(arm_pos);
        }
    }

    public void stop() {
        left_motor.setPower(0);
        right_motor.setPower(0);
        pulley_motor.setPower(0);
        winch_motor.setPower(0);
    }

    public void updateTime() {
        prev_time = time;
        time = System.currentTimeMillis();
    }

    public double getDelta() {
        return time - prev_time;
    }

    // pass position and signed rotational velocity in rad/s
    public double updateServo(double pos, double rv) {
        return Range.clip(pos + rv/(Math.PI) * getDelta()/1000, 0, 1);
    }
}

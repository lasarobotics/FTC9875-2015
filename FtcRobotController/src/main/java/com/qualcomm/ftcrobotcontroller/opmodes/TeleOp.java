package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by will on 10/6/15.
 */

public class TeleOp extends OpMode {
    DcMotor left_back_motor, left_front_motor, right_back_motor, right_front_motor, brush_motor, winch_motor;
    Servo arm_servo, bucket_servo;
    double speed_scale = 1;
    float trigger_threshold = 0.6f;
    double t = System.nanoTime();
    double last_t = 0;
    double dt = 0;
    double servo_rv = 10;

    public void init() {
        left_back_motor = hardwareMap.dcMotor.get("lb");
        left_front_motor = hardwareMap.dcMotor.get("lf");
        right_back_motor = hardwareMap.dcMotor.get("rb");
        right_front_motor = hardwareMap.dcMotor.get("rf");
	
        brush_motor = hardwareMap.dcMotor.get("b");
	    winch_motor = hardwareMap.dcMotor.get("w");
	
	    arm_servo = hardwareMap.servo.get("a");
	    bucket_servo = hardwareMap.servo.get("bk");
    }
    public void loop() {
        updateTime();

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

        if(gamepad1.dpad_up) {
            updateServo(arm_servo, servo_rv);
            updateServo(bucket_servo, -servo_rv);
        } else if (gamepad1.dpad_down) {
            updateServo(arm_servo, -servo_rv);
            updateServo(bucket_servo, servo_rv);
        }

	    if(gamepad1.left_trigger > trigger_threshold) {
          brush_motor.setPower(1);
        } else if(gamepad1.right_trigger > trigger_threshold) {
	      brush_motor.setPower(-1);
	    } else {
            brush_motor.setPower(0);
        }
    }
    public void stop() {
        left_back_motor.setPower(0);
        left_front_motor.setPower(0);
        right_back_motor.setPower(0);
        right_front_motor.setPower(0);
        brush_motor.setPower(0);
    }
    private void updateTime() {
        dt = t - last_t;
        last_t = t;
        t = System.nanoTime();
    }
    private void updateServo(Servo servo, double rv) {
        servo.setPosition(servo.getPosition() + dt/1000000 * (rv / 360));
    }
}

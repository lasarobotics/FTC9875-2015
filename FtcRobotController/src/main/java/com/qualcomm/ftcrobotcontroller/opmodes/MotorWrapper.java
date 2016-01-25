package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorWrapper {
    DcMotor internal;

    public MotorWrapper(OpMode context, String name) {
        internal = context.hardwareMap.dcMotor.get(name);
    }
    public void setPower(double power) {
        if(internal != null) {
            internal.setPower(power);
        } else {
            Log.e("Motor", internal.getDeviceName() + "is offline");
        }
    }
}

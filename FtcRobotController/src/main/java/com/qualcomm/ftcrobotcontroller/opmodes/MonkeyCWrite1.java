package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCWrite1 extends OpMode {
    private MonkeyCWrite monkeyCWrite = new MonkeyCWrite(this, false);

    public void init() { monkeyCWrite.init(); }

    public void loop() { monkeyCWrite.loop(); }

    public void stop() { monkeyCWrite.stop(); }
}

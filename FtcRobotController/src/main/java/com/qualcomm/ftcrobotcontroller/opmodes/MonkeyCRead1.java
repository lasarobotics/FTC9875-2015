package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MonkeyCRead1 extends OpMode {
    private MonkeyCRead monkeyCRead = new MonkeyCRead(this, false);

    public void init() { monkeyCRead.init(); }

    public void loop() { monkeyCRead.loop(); }

    public void stop() { monkeyCRead.stop(); }
}

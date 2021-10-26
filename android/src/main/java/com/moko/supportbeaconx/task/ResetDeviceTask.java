package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.ResetDeviceTask
 */
public class ResetDeviceTask extends OrderTask {

    public byte[] data = {(byte) 0x0b};

    public ResetDeviceTask(int responseType) {
        super(OrderType.resetDevice, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}

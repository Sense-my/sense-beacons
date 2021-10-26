package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.HardwareVersionTask
 */
public class HardwareVersionTask extends OrderTask {

    public byte[] data;

    public HardwareVersionTask(int responseType) {
        super(OrderType.hardwareVersion, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}

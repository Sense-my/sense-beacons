package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.AdvTxPowerTask
 */
public class AdvTxPowerTask extends OrderTask {

    public byte[] data;

    public AdvTxPowerTask(int responseType) {
        super(OrderType.advTxPower, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

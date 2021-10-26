package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.LockStateTask
 */
public class LockStateTask extends OrderTask {

    public byte[] data;

    public LockStateTask(int responseType) {
        super(OrderType.lockState, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

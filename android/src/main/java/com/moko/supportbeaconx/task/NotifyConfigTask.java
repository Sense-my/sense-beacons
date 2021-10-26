package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.NotifyConfigTask
 */
public class NotifyConfigTask extends OrderTask {

    public byte[] data;

    public NotifyConfigTask(int responseType) {
        super(OrderType.notifyConfig, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}

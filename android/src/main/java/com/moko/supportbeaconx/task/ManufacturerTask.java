package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.ManufacturerTask
 */
public class ManufacturerTask extends OrderTask {

    public byte[] data;

    public ManufacturerTask(int responseType) {
        super(OrderType.manufacturer, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}

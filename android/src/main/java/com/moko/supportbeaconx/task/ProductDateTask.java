package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.ProductDateTask
 */
public class ProductDateTask extends OrderTask {

    public byte[] data;

    public ProductDateTask(int responseType) {
        super(OrderType.productDate, responseType);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}

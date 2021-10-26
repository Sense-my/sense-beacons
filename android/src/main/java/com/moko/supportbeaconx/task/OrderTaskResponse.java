package com.moko.supportbeaconx.task;

import com.moko.supportbeaconx.entity.OrderType;

import java.io.Serializable;

/**
 * @Date 2018/1/23
 * @Author wenzheng.liu
 * @Description 任务反馈
 * @ClassPath com.moko.support.task.OrderTaskResponse
 */
public class OrderTaskResponse implements Serializable {
    public OrderType orderType;
    public int responseType;
    public byte[] responseValue;
}

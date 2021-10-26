package com.moko.supportbeaconx.event;

import com.moko.supportbeaconx.task.OrderTaskResponse;

public class OrderTaskResponseEvent {
    private String action;
    private OrderTaskResponse response;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public OrderTaskResponse getResponse() {
        return response;
    }

    public void setResponse(OrderTaskResponse response) {
        this.response = response;
    }
}

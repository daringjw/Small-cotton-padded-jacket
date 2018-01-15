package com.jinkun.care.ui.activity.order.model;

/**
 * Created by coderwjq on 2017/9/6 17:21.
 */

public class OrderSpinnerBean {
    private int id;
    private String value;

    public OrderSpinnerBean() {
    }

    public OrderSpinnerBean(int id, String value) {

        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderSpinnerBean{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}

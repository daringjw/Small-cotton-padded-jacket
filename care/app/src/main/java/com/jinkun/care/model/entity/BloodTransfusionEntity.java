package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/18 14:03.
 * @Desc
 */

public class BloodTransfusionEntity {
    private String transfusionReason;
    private String transfusionTime;

    public BloodTransfusionEntity(String transfusionReason, String transfusionTime) {

        this.transfusionReason = transfusionReason;
        this.transfusionTime = transfusionTime;
    }

    public String getTransfusionReason() {
        return transfusionReason;
    }

    public void setTransfusionReason(String transfusionReason) {
        this.transfusionReason = transfusionReason;
    }

    public String getTransfusionTime() {
        return transfusionTime;
    }

    public void setTransfusionTime(String transfusionTime) {
        this.transfusionTime = transfusionTime;
    }
}

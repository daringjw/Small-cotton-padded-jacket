package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/18 14:03.
 * @Desc
 */

public class SurgeryEntity {
    private String surgeryName;
    private String surgeryTime;

    public SurgeryEntity(String surgeryName, String surgeryTime) {
        this.surgeryName = surgeryName;
        this.surgeryTime = surgeryTime;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getSurgeryTime() {
        return surgeryTime;
    }

    public void setSurgeryTime(String surgeryTime) {
        this.surgeryTime = surgeryTime;
    }
}

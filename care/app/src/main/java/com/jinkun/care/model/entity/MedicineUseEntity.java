package com.jinkun.care.model.entity;

/**
 * Created by coderwjq on 2017/8/22 11:50.
 */

public class MedicineUseEntity {
    private String medicineName;
    private String morning;
    private String noon;
    private String night;
    private String beforeSleep;
    private String remarks;

    public MedicineUseEntity() {
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getBeforeSleep() {
        return beforeSleep;
    }

    public void setBeforeSleep(String beforeSleep) {
        this.beforeSleep = beforeSleep;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

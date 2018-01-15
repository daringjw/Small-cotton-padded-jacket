package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/18 14:03.
 * @Desc
 */

public class HospitalEntity {
    private String hospitalName;

    public HospitalEntity(String hospitalName) {

        this.hospitalName = hospitalName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}

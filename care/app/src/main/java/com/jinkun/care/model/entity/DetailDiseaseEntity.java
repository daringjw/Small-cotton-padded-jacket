package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/17 17:15.
 * @Desc
 */

public class DetailDiseaseEntity {
    private String diseaseName;
    private String diagnoseTime;
    private boolean isChecked;

    public DetailDiseaseEntity(String diseaseName, String diagnoseTime, boolean isChecked) {

        this.diseaseName = diseaseName;
        this.diagnoseTime = diagnoseTime;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiagnoseTime() {
        return diagnoseTime;
    }

    public void setDiagnoseTime(String diagnoseTime) {
        this.diagnoseTime = diagnoseTime;
    }
}

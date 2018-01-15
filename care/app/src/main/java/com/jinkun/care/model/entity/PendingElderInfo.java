package com.jinkun.care.model.entity;

/**
 * 老人信息对应的实体bean
 * Created by coderwjq on 2017/8/24 16:19.
 */

public class PendingElderInfo {
    // 老人照片路径
    private String imagePath;
    private String audioPath;
    private OldPeopleInfo mOldPeopleInfo;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public OldPeopleInfo getOldPeopleInfo() {
        return mOldPeopleInfo;
    }

    public void setOldPeopleInfo(OldPeopleInfo oldPeopleInfo) {
        mOldPeopleInfo = oldPeopleInfo;
    }

    @Override
    public String toString() {
        return "PendingElderInfo{" +
                "imagePath='" + imagePath + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", mOldPeopleInfo=" + mOldPeopleInfo +
                '}';
    }
}

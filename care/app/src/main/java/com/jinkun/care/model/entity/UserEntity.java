package com.jinkun.care.model.entity;

/**
 * @Created by coderwjq on 2017/8/15 17:59.
 * @Desc
 */

public class UserEntity {

    /**
     * address : 左安漪园
     * areaAddr : 北京市东城区
     * areaId : 110101
     * certificate1 :
     * certificate2 :
     * certificate3 :
     * creationTime : 0
     * email : vfuwenxiang@163.com
     * id : 212
     * latBD : 39.880798
     * latGG : 0
     * latGps : 0
     * linkName : 付文祥
     * linkPhone : 104
     * lngBD : 116.446956
     * lonGG : 0
     * lonGps : 0
     * name : 左安漪园养老服务驿站
     * pid : 1
     * requestTime : 1502699716678
     * state : 0
     * stationType : 51
     * userId : 4028807c5ddfdda9015ddfe2005d0000
     */

    private String address;
    private String areaAddr;
    private int areaId;
    private String certificate1;
    private String certificate2;
    private String certificate3;
    private long creationTime;
    private String email;
    private int id;
    private double latBD;
    private double latGG;
    private double latGps;
    private String linkName;
    private String linkPhone;
    private double lngBD;
    private double lonGG;
    private double lonGps;
    private String name;
    private int pid;
    private long requestTime;
    private int state;
    private int stationType;
    private String userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaAddr() {
        return areaAddr;
    }

    public void setAreaAddr(String areaAddr) {
        this.areaAddr = areaAddr;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getCertificate1() {
        return certificate1;
    }

    public void setCertificate1(String certificate1) {
        this.certificate1 = certificate1;
    }

    public String getCertificate2() {
        return certificate2;
    }

    public void setCertificate2(String certificate2) {
        this.certificate2 = certificate2;
    }

    public String getCertificate3() {
        return certificate3;
    }

    public void setCertificate3(String certificate3) {
        this.certificate3 = certificate3;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatBD() {
        return latBD;
    }

    public void setLatBD(double latBD) {
        this.latBD = latBD;
    }

    public double getLatGG() {
        return latGG;
    }

    public void setLatGG(double latGG) {
        this.latGG = latGG;
    }

    public double getLatGps() {
        return latGps;
    }

    public void setLatGps(double latGps) {
        this.latGps = latGps;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public double getLngBD() {
        return lngBD;
    }

    public void setLngBD(double lngBD) {
        this.lngBD = lngBD;
    }

    public double getLonGG() {
        return lonGG;
    }

    public void setLonGG(double lonGG) {
        this.lonGG = lonGG;
    }

    public double getLonGps() {
        return lonGps;
    }

    public void setLonGps(double lonGps) {
        this.lonGps = lonGps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStationType() {
        return stationType;
    }

    public void setStationType(int stationType) {
        this.stationType = stationType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "address='" + address + '\'' +
                ", areaAddr='" + areaAddr + '\'' +
                ", areaId=" + areaId +
                ", certificate1='" + certificate1 + '\'' +
                ", certificate2='" + certificate2 + '\'' +
                ", certificate3='" + certificate3 + '\'' +
                ", creationTime=" + creationTime +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", latBD=" + latBD +
                ", latGG=" + latGG +
                ", latGps=" + latGps +
                ", linkName='" + linkName + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", lngBD=" + lngBD +
                ", lonGG=" + lonGG +
                ", lonGps=" + lonGps +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", requestTime=" + requestTime +
                ", state=" + state +
                ", stationType=" + stationType +
                ", userId='" + userId + '\'' +
                '}';
    }
}

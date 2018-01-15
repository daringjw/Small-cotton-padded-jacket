package com.jinkun.care.ui.activity.order.model;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/6 8:54.
 */

public class VillageInfoBean {

    /**
     * code : success
     * msg : 请求成功
     * data : [{"address":"北京市朝阳区华腾园","areaId":110105,"createTime":1503029100415,"id":87,"latBD":39.893056,"lngBD":116.471842,"name":"华腾园","pensionId":1,"pointStr":"39.89444&116.46947,39.894412&116.474088,39.891714&116.474321,39.8917&116.469488","stationId":2},{"address":"北京市朝阳区劲松街道和谐雅园","areaId":110105,"createTime":1500514771229,"id":4,"latBD":39.893212,"lngBD":116.47659,"name":"和谐雅园","pensionId":1,"pointStr":"39.894372&116.474274,39.894427&116.479197,39.892157&116.479161,39.891687&116.474526","stationId":2},{"address":"北京市朝阳区劲松街道首城国际","areaId":110105,"createTime":1500514741041,"id":2,"latBD":39.895401,"lngBD":116.475509,"name":"首城国际","pensionId":1,"pointStr":"39.897084&116.473699,39.897195&116.479125,39.894482&116.479125,39.894455&116.474238","stationId":2}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VillageInfoBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * address : 北京市朝阳区华腾园
         * areaId : 110105
         * createTime : 1503029100415
         * id : 87
         * latBD : 39.893056
         * lngBD : 116.471842
         * name : 华腾园
         * pensionId : 1
         * pointStr : 39.89444&116.46947,39.894412&116.474088,39.891714&116.474321,39.8917&116.469488
         * stationId : 2
         */

        private String address;
        private int areaId;
        private long createTime;
        private int id;
        private double latBD;
        private double lngBD;
        private String name;
        private int pensionId;
        private String pointStr;
        private int stationId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public double getLngBD() {
            return lngBD;
        }

        public void setLngBD(double lngBD) {
            this.lngBD = lngBD;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPensionId() {
            return pensionId;
        }

        public void setPensionId(int pensionId) {
            this.pensionId = pensionId;
        }

        public String getPointStr() {
            return pointStr;
        }

        public void setPointStr(String pointStr) {
            this.pointStr = pointStr;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "address='" + address + '\'' +
                    ", areaId=" + areaId +
                    ", createTime=" + createTime +
                    ", id=" + id +
                    ", latBD=" + latBD +
                    ", lngBD=" + lngBD +
                    ", name='" + name + '\'' +
                    ", pensionId=" + pensionId +
                    ", pointStr='" + pointStr + '\'' +
                    ", stationId=" + stationId +
                    '}';
        }
    }
}

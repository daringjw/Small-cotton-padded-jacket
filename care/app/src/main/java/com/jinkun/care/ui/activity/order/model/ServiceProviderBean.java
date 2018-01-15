package com.jinkun.care.ui.activity.order.model;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/6 8:54.
 */

public class ServiceProviderBean {

    /**
     * code : success
     * msg : 请求成功
     * data : [{"createTime":1503483338171,"id":17,"level":10,"providerId":1,"providerName":"和谐酒家","stationId":2,"stationName":"和谐雅园服务驿站","typeId":1,"typeName":"助餐服务"}]
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

    public static class DataBean {
        /**
         * createTime : 1503483338171
         * id : 17
         * level : 10
         * providerId : 1
         * providerName : 和谐酒家
         * stationId : 2
         * stationName : 和谐雅园服务驿站
         * typeId : 1
         * typeName : 助餐服务
         */

        private long createTime;
        private int id;
        private int level;
        private int providerId;
        private String providerName;
        private int stationId;
        private String stationName;
        private int typeId;
        private String typeName;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getProviderId() {
            return providerId;
        }

        public void setProviderId(int providerId) {
            this.providerId = providerId;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}

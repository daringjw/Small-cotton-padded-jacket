package com.jinkun.care.ui.activity.order.model;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/6 8:54.
 */

public class ServiceWaiterBean {
    /**
     * code : success
     * msg : 请求成功
     * data : [{"address":"阿斯顿发撒的发生的法国","age":25,"birthday":721152000000,"createTime":1504524014902,"headImg":"uploadFile/1504524010443KQ70D.png","id":7,"idCard":"110101199211081223","name":"张志平","phone":"13611301918","sex":1,"stationId":222,"userId":"4028855e5e4c53b7015e4c9e95610001","userType":0}]
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
         * address : 阿斯顿发撒的发生的法国
         * age : 25
         * birthday : 721152000000
         * createTime : 1504524014902
         * headImg : uploadFile/1504524010443KQ70D.png
         * id : 7
         * idCard : 110101199211081223
         * name : 张志平
         * phone : 13611301918
         * sex : 1
         * stationId : 222
         * userId : 4028855e5e4c53b7015e4c9e95610001
         * userType : 0
         */

        private String address;
        private int age;
        private long birthday;
        private long createTime;
        private String headImg;
        private int id;
        private String idCard;
        private String name;
        private String phone;
        private int sex;
        private int stationId;
        private String userId;
        private int userType;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}

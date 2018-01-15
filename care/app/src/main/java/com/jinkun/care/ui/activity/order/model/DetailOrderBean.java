package com.jinkun.care.ui.activity.order.model;

/**
 * 小棉袄工单
 *
 * @author Administrator
 */
public class DetailOrderBean {
    private Integer id;
    private String orderId;//服务单号
    private Integer oldId; // 老人ID
    private Integer age;//老人年龄
    private Integer acceptId;//接单人ID
    private Integer stationId;//站点ID
    private Integer pensionId;//所属机构ID
    private String name;//老人姓名
    private String phone;//联系电话
    private Integer projectId;//服务项目ID
    private String projectName;//服务项目名称
    private Integer providerId;//服务单位
    private String providerName;//服务单位名称
    private String content;//服务内容
    private String address;//服务地址
    private Integer communityId;//社区ID
    private Long dates;//服务日期
    private String startEndTime;//服务开始日期
    private Integer standard;//服务是否规范（1：规范 2：不规范）
    private Integer state;//服务是否满意（0：很满意  1：基本满意   2不满意）
    private String serviceType;//服务方式
    private String opinion;//意见、
    private String guardian;//监护人
    private Integer staffId; //服务人员ID
    private String staffName;//服务人员姓名
    private Integer orderState; // 0初始录入，1已选定服务商，2已派单， 3服务中，4,服务结束，5已回访，6已完成
    private Float singlePrice; // 服务单价  (1)
    private Integer serviceUnit; // 时长/数量 (1)
    private Float countPrice; // 服务总价 (1)
    private Float deliveryCost; // 配送费
    private String remarks;//备注
    private Integer payState;//支付状态 0未支付、1现金、2微信、3支付宝、4储值卡
    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Integer acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getPensionId() {
        return pensionId;
    }

    public void setPensionId(Integer pensionId) {
        this.pensionId = pensionId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Long getDates() {
        return dates;
    }

    public void setDates(Long dates) {
        this.dates = dates;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Float getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Float singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Integer getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(Integer serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public Float getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(Float countPrice) {
        this.countPrice = countPrice;
    }

    public Float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

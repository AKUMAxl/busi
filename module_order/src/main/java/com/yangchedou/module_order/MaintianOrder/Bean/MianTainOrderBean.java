package com.yangchedou.module_order.MaintianOrder.Bean;

/**
 * Created by ADMIN on 2017/11/18.
 */

public class MianTainOrderBean {


    /**
     * carNo : POLO
     * customerId : 1460
     * orderCode : 092117311508301
     * registerLogin : 13354221918
     * useticketPrice : 0
     * orderName : 预购券（预付30元立省70元）
     * state : 0
     * payWay : 线上
     * paystateName : 已支付
     * orderPrice : 30
     * registerPhone : 13354221918
     * createDate : 2016-09-21 17:31:15
     * orderId : 2174
     */

    private String carNo;
    private int customerId;
    private String orderCode;
    private String registerLogin;
    private double useticketPrice;
    private String orderName;
    private String state;
    private String payWay;
    private String paystateName;
    private double orderPrice;
    private String registerPhone;
    private String createDate;
    private int orderId;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRegisterLogin() {
        return registerLogin;
    }

    public void setRegisterLogin(String registerLogin) {
        this.registerLogin = registerLogin;
    }

    public double getUseticketPrice() {
        return useticketPrice;
    }

    public void setUseticketPrice(double useticketPrice) {
        this.useticketPrice = useticketPrice;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPaystateName() {
        return paystateName;
    }

    public void setPaystateName(String paystateName) {
        this.paystateName = paystateName;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "MianTainOrderBean{" +
                "carNo='" + carNo + '\'' +
                ", customerId=" + customerId +
                ", orderCode='" + orderCode + '\'' +
                ", registerLogin='" + registerLogin + '\'' +
                ", useticketPrice=" + useticketPrice +
                ", orderName='" + orderName + '\'' +
                ", state='" + state + '\'' +
                ", payWay='" + payWay + '\'' +
                ", paystateName='" + paystateName + '\'' +
                ", orderPrice=" + orderPrice +
                ", registerPhone='" + registerPhone + '\'' +
                ", createDate='" + createDate + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}

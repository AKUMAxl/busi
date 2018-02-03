package com.yangchedou.lib_common;

import java.util.List;

/**
 * Created by ADMIN on 2018/1/5.
 */

public class MaintianOrderDetialBean {


    /**
     * orderCode : 090609510753601
     * orderDate : 2016-09-06 09:51:07
     * carBrand : 东风本田(思域 2014 款 1.8L 自动 LXi经典版)
     * orderName : 预购券（预付30元立省70元）
     * state : 0
     * pictureList :
     * detailList : [{"con":1,"price":30,"name":"预购券（预付30元立省70元）"}]
     * registerPhone : 13504182181
     * cancelReason :
     * message : 查询成功
     * id : 1116
     * path : http://120.25.152.168:8081/cars/
     * payWay : 1
     * orderPrice : 30
     * registerName : 13504182181
     * createDate : 2016-09-06 09:51:07
     * success : true
     * orderId : 2067
     */

    private String orderCode;
    private String orderDate;
    private String carBrand;
    private String orderName;
    private String state;
    private String pictureList;
    private String registerPhone;
    private String cancelReason;
    private String message;
    private int id;
    private String path;
    private String payWay;
    private int orderPrice;
    private String registerName;
    private String createDate;
    private boolean success;
    private int orderId;
    private List<DetailListBean> detailList;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
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

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * con : 1
         * price : 30
         * name : 预购券（预付30元立省70元）
         */

        private int con;
        private int price;
        private String name;

        public int getCon() {
            return con;
        }

        public void setCon(int con) {
            this.con = con;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DetailListBean{" +
                    "con=" + con +
                    ", price=" + price +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MaintianOrderDetialBean{" +
                "orderCode='" + orderCode + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", orderName='" + orderName + '\'' +
                ", state='" + state + '\'' +
                ", pictureList='" + pictureList + '\'' +
                ", registerPhone='" + registerPhone + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                ", path='" + path + '\'' +
                ", payWay='" + payWay + '\'' +
                ", orderPrice=" + orderPrice +
                ", registerName='" + registerName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", success=" + success +
                ", orderId=" + orderId +
                ", detailList=" + detailList +
                '}';
    }
}

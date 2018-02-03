package com.yangchedou.lib_common.Base.MultiSelect;

import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public class MultiSelectStringBean {


    /**
     * message : 查询成功
     * path : http://192.168.1.121:8081/cars/
     * list : [{"name":"洗车"},{"name":"更换机油机油滤芯"},{"name":"机油"},{"name":"机滤"},{"name":"空气滤芯"},{"name":"空调滤芯"},{"name":"PM2.5"},{"name":"燃油滤芯"},{"name":"更换防冻液"},{"name":"更换制动液"},{"name":"更换助力油"},{"name":"更换变速箱油"},{"name":"润滑系统清洗"},{"name":"润滑系统保养"},{"name":"清洗节气门"},{"name":"清洗喷油嘴"},{"name":"清洗三元催化"},{"name":"清洗燃烧室"},{"name":"燃油系统保护剂"},{"name":"发动机舱护理"},{"name":"辛烷值提升剂"},{"name":"更换雨刷器片"},{"name":"更换火花塞"},{"name":"更换前制动片"},{"name":"更换后刹车片"},{"name":"更换前制动盘"},{"name":"更换后制动盘"},{"name":"电脑检测"},{"name":"电脑匹配"},{"name":"空调充冷媒"},{"name":"线路维修"},{"name":"更换电瓶"},{"name":"加装氙气大灯"},{"name":"更换灯泡"},{"name":"更换前大灯"},{"name":"更换尾灯"},{"name":"更换转向灯"},{"name":"前后杠"},{"name":"发动机罩"},{"name":"后备箱盖"},{"name":"翼子板"},{"name":"车门"},{"name":"更换轮胎"},{"name":"单轮动平衡"},{"name":"四轮对调轮胎"},{"name":"两轮对调轮胎"},{"name":"四轮定位"},{"name":"免费检测"},{"name":"上门维修"},{"name":"凹陷修复"},{"name":"玻璃修复"},{"name":"轮胎"},{"name":"轮毂"},{"name":"一元购玻璃水套装"},{"name":"电瓶连电"},{"name":" 补胎"},{"name":"底盘检测"},{"name":"电瓶"},{"name":"前轮轴承"},{"name":"后轮轴承"},{"name":"前减震器"},{"name":"后减震器"},{"name":"正时皮带"},{"name":"更换发电机皮带"},{"name":"更换水泵"},{"name":"更换节温器"},{"name":"机油去"},{"name":"5W-30 sn"},{"name":"dsa"},{"name":""},{"name":"31321"},{"name":"保养"},{"name":"活动"},{"name":"养车豆小保养套餐合成油(壳牌X7) 4升"},{"name":"养车豆小保养套餐全合成油(壳牌X8) 4升"}]
     * success : true
     */

    private String message;
    private String path;
    private boolean success;
    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 洗车
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

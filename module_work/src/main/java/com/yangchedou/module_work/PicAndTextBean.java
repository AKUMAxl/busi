package com.yangchedou.module_work;

/**
 * Created by ADMIN on 2017/11/13.
 */

public class PicAndTextBean {

    private int resId;
    private int strId;
    private int unreadCount;

    public PicAndTextBean() {
    }

    public PicAndTextBean(int resId, int strId, int unreadCount) {
        this.resId = resId;
        this.strId = strId;
        this.unreadCount = unreadCount;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getStrId() {
        return strId;
    }

    public void setStrId(int strId) {
        this.strId = strId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    @Override
    public String toString() {
        return "PicAndTextBean{" +
                "resId=" + resId +
                ", strId=" + strId +
                ", unreadCount=" + unreadCount +
                '}';
    }
}

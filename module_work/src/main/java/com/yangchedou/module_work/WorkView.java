package com.yangchedou.module_work;

/**
 * Created by ADMIN on 2017/11/13.
 */

public interface WorkView {

    void maintainCount(int unreadCount);

    void washCount(int unreadCount);

    void changeShopState(boolean onBusi);

    void showToast(String text);

    void showDialog(String text);

    void dismissDialog();

    void loadPhoto(String url);

    void setBusiName(String name);

    void setBusiId(String id);
}

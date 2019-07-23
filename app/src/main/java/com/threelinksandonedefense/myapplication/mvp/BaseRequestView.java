package com.threelinksandonedefense.myapplication.mvp;

/**
 * Created by 张成昆 on 2019-6-22.
 */

public interface BaseRequestView extends BaseView {
    void onRequestError(String msg);
}


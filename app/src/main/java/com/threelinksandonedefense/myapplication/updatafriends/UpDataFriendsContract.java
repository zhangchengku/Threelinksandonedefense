package com.threelinksandonedefense.myapplication.updatafriends;

import android.app.Activity;
import android.content.Context;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;
import com.threelinksandonedefense.myapplication.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UpDataFriendsContract {
    interface View extends BaseRequestView {
        void getData(List<UpDataInitBean.DATABean> s);
        void getReuslt(String s);
    }

    interface  Presenter extends BasePresenter<View> {
        void initData(String cjid, Activity Activity);
        void upData(String json, Activity Activity);
    }
}

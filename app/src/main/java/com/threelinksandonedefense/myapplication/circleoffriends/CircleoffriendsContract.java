package com.threelinksandonedefense.myapplication.circleoffriends;

import android.app.Activity;
import android.content.Context;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;
import com.threelinksandonedefense.myapplication.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CircleoffriendsContract {
    interface View extends BaseRequestView {
        void getData(String s);
//        void getData2();
    }

    interface  Presenter extends BasePresenter<View> {
        void upData(String xmid, Activity Activity);
    }
}

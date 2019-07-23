package com.threelinksandonedefense.myapplication.land;

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

public class LandContract {
    interface View extends BaseRequestView {
        void getLand(List<LandBean.DATABean> s);
    }

    interface  Presenter extends BasePresenter<View> {
        void Land(String username, String password, Activity Activity);
    }
}

package com.threelinksandonedefense.myapplication.monthlyeport;

import android.app.Activity;
import android.content.Context;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseView;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MonthlyeportContract {
    interface View extends BaseRequestView {
        void getData(InitDataBean s);
        void getData2();
        void getData3();
    }

    interface  Presenter extends BasePresenter<View> {
        void initdata(String xmid, Activity Activity);
        void SaveJdybInfo(String json, Activity Activity);
        void addPic(String json, Activity Activity);
    }
}

package com.threelinksandonedefense.myapplication.completesectionfilling;

import android.app.Activity;
import android.content.Context;

import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CompleteSectionFillingContract {
    interface View extends BaseRequestView {
        void getData(CompleteInitDataBean.DATABean s);
        void getData2();
        void getData3();
    }

    interface  Presenter extends BasePresenter<View> {
        void initdata(String xmid, Activity Activity);
        void SaveJdybInfo(String json,String xmmc,   Activity Activity);
        void addPic(String json, Activity Activity);
    }
}

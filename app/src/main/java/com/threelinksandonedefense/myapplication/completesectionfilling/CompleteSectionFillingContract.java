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
    }

    interface  Presenter extends BasePresenter<View> {
        void initdata(String xmid, Activity Activity);
    }
}

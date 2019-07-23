package com.threelinksandonedefense.myapplication.map;

import android.app.Activity;


import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;
import com.threelinksandonedefense.myapplication.mvp.BaseView;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MapContract {
    interface View extends BaseRequestView {
        void getDatas(MdBean.RoadInfo s);
        void getPois(PoiBean.ResultsBean ss);
void getPqis(PqiBean.PCIInfo sss);
    }

    interface  Presenter extends BasePresenter<View> {
        void getData( String token ,Activity Activity);
        void getPoi( String token ,Activity Activity);
        void getPqi(String token,Activity Activity);
    }
}

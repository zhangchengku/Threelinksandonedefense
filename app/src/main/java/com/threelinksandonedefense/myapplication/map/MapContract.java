package com.threelinksandonedefense.myapplication.map;

import android.app.Activity;


import com.threelinksandonedefense.myapplication.mvp.BasePresenter;
import com.threelinksandonedefense.myapplication.mvp.BaseRequestView;
import com.threelinksandonedefense.myapplication.mvp.BaseView;

import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MapContract {
    interface View extends BaseRequestView {
        void getPois(PoiBean ss);
        void getPointClicks(List<PointClickBean.DATABean> ss);
        void getTunnels(TunnelBean ss);
        void getTunnelClicks(List<TunnelClickBean.DATABean> ss);
        void getSclerosis20s(Sclerosis20Bean ss);
        void getSclerosis20Clicks(List<Sclerosis20Click.DATABean> ss);
    }

    interface  Presenter extends BasePresenter<View> {
        void getPoi( String token ,Activity Activity);
        void getPointClick(String token,Activity Activity);
        void getTunnel(String token,Activity Activity);
        void getTunnelClick(String token,Activity Activity);
        void getSclerosis20(String xmlx,String token,Activity Activity);
        void getSclerosis20Click(String token,Activity Activity);
    }
}

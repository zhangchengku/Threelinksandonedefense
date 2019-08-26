package com.threelinksandonedefense.myapplication.monthlyeport;

import android.app.Activity;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.BaseBean;
import com.threelinksandonedefense.myapplication.Urls;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;
import com.threelinksandonedefense.myapplication.mvp.BasePresenterImpl;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MonthlyeportPresenter extends BasePresenterImpl<MonthlyeportContract.View> implements MonthlyeportContract.Presenter{
    @Override
    public void initdata(String xmid,  Activity Activity) {
        OkGo.<String>get(Urls.SERVER + "GDSTYF/InitJdybInfo")
                .params("xmid", xmid)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        InitDataBean initDataBean = JSON.parseObject(response.body(),InitDataBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData(initDataBean);
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });
    }
    @Override
    public void SaveJdybInfo(String json,  Activity Activity) {
        OkGo.<String>post(Urls.SERVER + "GDSTYF/SaveJdybInfo")
                .params("json", json)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean initDataBean = JSON.parseObject(response.body(),BaseBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData2();
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });
    }
    @Override
    public void addPic(String json,  Activity Activity) {
        OkGo.<String>post(Urls.SERVER + "GDSTYF/SaveXmJdImageInfo")
                .params("json", json)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean initDataBean = JSON.parseObject(response.body(),BaseBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData3();
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });
    }
}

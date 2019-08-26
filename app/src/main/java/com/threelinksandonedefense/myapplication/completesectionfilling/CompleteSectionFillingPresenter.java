package com.threelinksandonedefense.myapplication.completesectionfilling;

import android.app.Activity;
import android.content.Context;

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

public class CompleteSectionFillingPresenter extends BasePresenterImpl<CompleteSectionFillingContract.View> implements CompleteSectionFillingContract.Presenter{
    @Override
    public void initdata(String xmid,  Activity Activity) {
        OkGo.<String>get(Urls.SERVER + "GDSTYF/InitGcldInfo")
                .params("xmid", xmid)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CompleteInitDataBean initDataBean = JSON.parseObject(response.body(),CompleteInitDataBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData(initDataBean.getDATA().get(0));
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });
    }
    @Override
    public void SaveJdybInfo(String json,String xmmc,  Activity Activity) {
        OkGo.<String>post(Urls.SERVER + "GDSTYF/SaveGcldInfo")
                .params("json", json)
                .params("xmmc", xmmc)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean initDataBean = JSON.parseObject(response.body(),BaseBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData2();
                        }else {
                            mView.onRequestError(initDataBean.getDATA().toString());
                        }
                    }
                });
    }
    @Override
    public void addPic(String json,  Activity Activity) {
        OkGo.<String>post(Urls.SERVER + "GDSTYF/SaveGcldImageInfo")
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

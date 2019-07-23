package com.threelinksandonedefense.myapplication.completesectionfilling;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
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
        OkGo.<String>get(Urls.SERVER + "InitGcldInfo")
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
}

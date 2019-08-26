package com.threelinksandonedefense.myapplication.land;

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

public class LandPresenter extends BasePresenterImpl<LandContract.View> implements LandContract.Presenter{
    @Override
    public void Land(String username, String password, Activity Activity) {
        OkGo.<String>get(Urls.SERVER + "GDSTYF/Login")
                .params("uname", username)
                .params("pwd", password)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LandBean landBean = JSON.parseObject(response.body(),LandBean.class);
                        if (mView == null)
                            return;
                        if (landBean.getSTATE().equals("1")){
                            mView.getLand(landBean.getDATA());
                        }else {
                            mView.onRequestError(landBean.getMSG());
                        }
                    }
                });
    }
}

package com.threelinksandonedefense.myapplication.circleoffriends;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.BaseBean;
import com.threelinksandonedefense.myapplication.Urls;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;
import com.threelinksandonedefense.myapplication.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CircleoffriendsPresenter extends BasePresenterImpl<CircleoffriendsContract.View> implements CircleoffriendsContract.Presenter{
    @Override
    public void upData(String json,  Activity Activity) {
        OkGo.<String>post(Urls.SERVER + "SaveCjInfo")
                .params("json", json)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean initDataBean = JSON.parseObject(response.body(),BaseBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData(initDataBean.getSTATE());
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });

    }
}

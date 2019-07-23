package com.threelinksandonedefense.myapplication.updatafriends;

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

public class UpDataFriendsPresenter extends BasePresenterImpl<UpDataFriendsContract.View> implements UpDataFriendsContract.Presenter{
    @Override
    public void initData(String cjid,  Activity Activity) {
        OkGo.<String>get(Urls.SERVER + "QueryCjInfoByCjId")
                .params("cjid", cjid)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UpDataInitBean initDataBean = JSON.parseObject(response.body(),UpDataInitBean.class);
                        if (mView == null)
                            return;
                        if (initDataBean.getSTATE().equals("1")){
                            mView.getData(initDataBean.getDATA());
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });

    }
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
                            mView.getReuslt(initDataBean.getSTATE());
                        }else {
                            mView.onRequestError(initDataBean.getMSG());
                        }
                    }
                });

    }
}

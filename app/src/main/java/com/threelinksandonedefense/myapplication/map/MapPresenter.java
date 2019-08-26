package com.threelinksandonedefense.myapplication.map;

import android.app.Activity;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.Urls;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;
import com.threelinksandonedefense.myapplication.mvp.BasePresenterImpl;
import com.threelinksandonedefense.myapplication.utils.Gzip;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.io.IOException;
/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */
public class MapPresenter extends BasePresenterImpl<MapContract.View> implements MapContract.Presenter{
    @Override
    public void getPoi(String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LocationForQL")
                .params("areaCode",token)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PoiBean poiBean = JSON.parseObject(response.body(),PoiBean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getPois(poiBean);
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
    @Override
    public void getPointClick(String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LoadForQL")
                .params("guid_obj",token)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PointClickBean poiBean = JSON.parseObject(response.body(),PointClickBean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getPointClicks(poiBean.getDATA());
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
    @Override
    public void getTunnel(String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LocationForSD")
                .params("areaCode",token)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        TunnelBean poiBean = JSON.parseObject(response.body(),TunnelBean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getTunnels(poiBean);
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
    @Override
    public void getTunnelClick(String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LoadForSD")
                .params("guid_obj",token)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        TunnelClickBean poiBean = JSON.parseObject(response.body(),TunnelClickBean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getTunnelClicks(poiBean.getDATA());
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
    @Override
    public void getSclerosis20(String xmlx,String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LocationForXM")
                .params("areaCode",token)
                .params("xmlx",xmlx)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Sclerosis20Bean poiBean = JSON.parseObject(response.body(),Sclerosis20Bean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getSclerosis20s(poiBean);
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
    @Override
    public void getSclerosis20Click(String token,Activity Activity) {
        OkGo.<String>get(Urls.SERVER+"Common/LoadForXM")
                .params("guid_obj",token)
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Sclerosis20Click poiBean = JSON.parseObject(response.body(),Sclerosis20Click.class);
                        if (mView == null)
                            return;
                        if (poiBean.getSTATE().equals("1")){
                            mView.getSclerosis20Clicks(poiBean.getDATA());
                        }
                        Log.e( "张成昆: ",poiBean.getSTATE() );
                    }
                });
    }
}

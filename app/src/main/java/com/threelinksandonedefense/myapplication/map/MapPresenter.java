package com.threelinksandonedefense.myapplication.map;

import android.app.Activity;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
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
    public void getData(String token,Activity Activity) {
        OkGo.<String>get("http://106.37.229.146:1213/iRoadService.svc/ZGGK_GET_ORAD_BY_POINT")
                .params("version","1.1")
                .params("token",token)
                .params("province_code","440000")
                .params("longitude","114.83458211116516")
                .params("latitude","23.84719486164605")
                .params("latitude","23.84719486164605")
                .params("click_province_code","440000")
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String json = Gzip.unGzip(response.body().toString());
                            MdBean landBean = JSON.parseObject(json,MdBean.class);
                            Log.e( "张成昆: ",landBean.getStatus() );
                            if (mView == null)
                                return;
                            if (landBean.getStatus().equals("Y")){
                                mView.getDatas(landBean.getResults());
                            }else {
                                mView.onRequestError(landBean.getError_msg());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    @Override
    public void getPoi(String token,Activity Activity) {
        OkGo.<String>get("http://106.37.229.146:1213/iRoadService.svc/ZGGK_PROVINCE_tunnel")
                .params("version","1.1")
                .params("province_code","440000")
                .params("token",token)
                .params("tunnel_type","SUPER_LARGE_TUNNEL")
                .params("is_get_detail","Y")
                .params("city_code","")
                .params("county_code","")
                .params("is_45_type","N")
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PoiBean poiBean = JSON.parseObject(response.body(),PoiBean.class);
                        if (mView == null)
                            return;
                        if (poiBean.getStatus().equals("Y")){
                            mView.getPois(poiBean.getResults());
                        }else {
                            mView.onRequestError(poiBean.getError_msg());
                        }
                        Log.e( "张成昆: ",poiBean.getStatus() );
                    }
                });
    }
    @Override
    public void getPqi(String token,Activity Activity) {
        OkGo.<String>get("http://106.37.229.146:1213/iRoadService.svc/ZGGK_ROAD_PCI_SYSTEM")
                .params("version","1.1")
                .params("province_code","north")
                .params("token",token)
                .params("road_code","G2001")
                .params("indicator_id","pqi")
                .execute(new StringDialogCallback(Activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String json = Gzip.unGzip(response.body().toString());
                            PqiBean poiBean = JSON.parseObject(json,PqiBean.class);
                            if (mView == null)
                                return;
                            if (poiBean.getStatus().equals("Y")){
                                mView.getPqis(poiBean.getResults());
                            }else {
                                mView.onRequestError(poiBean.getError_msg());
                            }
                            Utils.i("张成昆",json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

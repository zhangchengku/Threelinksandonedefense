package com.threelinksandonedefense.myapplication.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.amap.api.services.route.RouteSearch.DRIVING_SINGLE_NO_HIGHWAY;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
public class MapActivity extends MVPBaseActivity<MapContract.View, MapPresenter> implements MapContract.View{
    @Bind(R.id.map)
    MapView mMapView;
    @Bind(R.id.lin)
    Button lin;
    @Bind(R.id.poi)
    Button poi;
    @Bind(R.id.pqi)
    Button pqi;
    @Bind(R.id.sf)
    Button sf;
    private AMap aMap = null;
    private RouteSearch routeSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        initMap();
        linstener();
    }

    private void linstener() {
        poi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeSearch = new RouteSearch(MapActivity.this);
                LatLonPoint startPoint = new LatLonPoint(38.032931, 114.514011);//石家庄
                LatLonPoint endPoint = new LatLonPoint(38.867495, 115.458835);
                RouteSearch.FromAndTo  fromAndTo=   new RouteSearch.FromAndTo(startPoint,endPoint);
                List<LatLonPoint> sss = new ArrayList<>();//114.807163,38.644015
                LatLonPoint LatLonPoint = new LatLonPoint(38.644015,114.807163);
                sss.add(LatLonPoint);
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, DRIVING_SINGLE_NO_HIGHWAY, sss, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
                routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
                    @Override
                    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

                    }

                    @Override
                    public void onDriveRouteSearched(DriveRouteResult result, int i) {
                        List<LatLonPoint> polyline = new ArrayList<>();
                        List<DrivePath> drivePathList = result.getPaths();
                        DrivePath drivePath = drivePathList.get(0);
                        List<DriveStep> steps = drivePath.getSteps();
                        for (DriveStep step : steps) {
                            polyline.addAll(step.getPolyline()) ;
                        }
                        List<LatLng> latLngs = new ArrayList<LatLng>();
                        for (int k = 0; k < polyline.size(); k++) {
                            latLngs.add(new LatLng(polyline.get(k).getLatitude(), polyline.get(k).getLongitude()));
                        }
                        aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(Color.RED));
                    }

                    @Override
                    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

                    }

                    @Override
                    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

                    }
                });
            }
        });
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }

















    @Override
    public void getDatas(MdBean.RoadInfo s) {

    }

    @Override
    public void getPois(PoiBean.ResultsBean ss) {

    }

    @Override
    public void getPqis(PqiBean.PCIInfo sss) {

    }
}

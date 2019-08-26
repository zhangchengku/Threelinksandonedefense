package com.threelinksandonedefense.myapplication.map;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.bumptech.glide.Glide;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
public class MapActivity extends MVPBaseActivity<MapContract.View, MapPresenter> implements MapContract.View, DistrictSearch.OnDistrictSearchListener {


    @Bind(R.id.map)
    MapView mMapView;
    @Bind(R.id.sclerosis20_lay)
    LinearLayout sclerosis20Lay;
    @Bind(R.id.sclerosis200_lay)
    LinearLayout sclerosis200Lay;
    @Bind(R.id.nobstructed_lay)
    LinearLayout nobstructedLay;
    @Bind(R.id.modification_lay)
    LinearLayout modificationLay;
    @Bind(R.id.equivalent_lay)
    LinearLayout equivalentLay;
    @Bind(R.id.mores_lay)
    RelativeLayout moresLay;
    @Bind(R.id.road_lay)
    LinearLayout roadLay;
    @Bind(R.id.bridge_lay)
    LinearLayout bridgeLay;
    @Bind(R.id.tunnel_lay)
    LinearLayout tunnelLay;
    @Bind(R.id.more_lay)
    LinearLayout moreLay;
    @Bind(R.id.menu_lay)
    LinearLayout menuLay;
    @Bind(R.id.back_lay)
    RelativeLayout backLay;
    @Bind(R.id.bridge_name)
    TextView bridgeName;
    @Bind(R.id.delete)
    TextView delete;
    @Bind(R.id.halving_line)
    View halvingLine;
    @Bind(R.id.bridge_img)
    ImageView bridgeImg;
    @Bind(R.id.bridge_wz)
    TextView bridgeWz;
    @Bind(R.id.bridge_fl)
    TextView bridgeFl;
    @Bind(R.id.qc)
    TextView qc;
    @Bind(R.id.unstarted_te)
    TextView unstartedTe;
    @Bind(R.id.wkg_lay)
    LinearLayout wkgLay;
    @Bind(R.id.construction_te)
    TextView constructionTe;
    @Bind(R.id.completed_te)
    TextView completedTe;
    @Bind(R.id.type_lay)
    RelativeLayout typeLay;
    @Bind(R.id.obvious_lay)
    RelativeLayout obviousLay;
    @Bind(R.id.time_te)
    TextView timeTe;
    @Bind(R.id.sjsj_lay)
    LinearLayout sjsjLay;
    @Bind(R.id.wctz_te)
    TextView wctzTe;
    @Bind(R.id.wctz_lay)
    LinearLayout wctzLay;
    @Bind(R.id.time_lay)
    RelativeLayout timeLay;
    @Bind(R.id.state_te)
    TextView stateTe;
    private AMap aMap = null;
    private String Token = "";
    private Map<Integer, Drawable> mBackDrawAbles = new HashMap<Integer, Drawable>();
    private LatLonPoint centerLatLng;
    private MultiPointOverlayOptions smallOptions;
    private MultiPointOverlayOptions largeOptions;
    private MultiPointOverlayOptions extralargeOptions;
    private List<MultiPointItem> smalllist = new ArrayList<MultiPointItem>();
    private List<MultiPointItem> largelist = new ArrayList<MultiPointItem>();
    private List<MultiPointItem> extralargelist = new ArrayList<MultiPointItem>();
    private int TYPE;
    private String Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        Token = getIntent().getStringExtra("Code");
        if (Token.equals("440000")) {
            Token = "";
            Name = "广东省";
        }else  if (Token.equals("440100")) {
            Name = "广州市";
        }else  if (Token.equals("440200")) {
            Name = "韶关市";
        }else  if (Token.equals("440300")) {
            Name = "深圳市";
        }else  if (Token.equals("440400")) {
            Name = "珠海市";
        }else  if (Token.equals("440500")) {
            Name = "汕头市";
        }else  if (Token.equals("440600")) {
            Name = "佛山市";
        }else  if (Token.equals("440700")) {
            Name = "江门市";
        }else  if (Token.equals("440800")) {
            Name = "湛江市";
        }else  if (Token.equals("440900")) {
            Name = "茂名市";
        }else  if (Token.equals("441200")) {
            Name = "肇庆市";
        }else  if (Token.equals("441300")) {
            Name = "惠州市";
        }else  if (Token.equals("441400")) {
            Name = "梅州市";
        }else  if (Token.equals("441500")) {
            Name = "汕尾市";
        }else  if (Token.equals("441600")) {
            Name = "河源市";
        }else  if (Token.equals("441700")) {
            Name = "阳江市";
        }else  if (Token.equals("441800")) {
            Name = "清远市";
        }else  if (Token.equals("441900")) {
            Name = "东莞市";
        }else  if (Token.equals("442000")) {
            Name = "中山市";
        }else  if (Token.equals("445100")) {
            Name = "潮州市";
        }else  if (Token.equals("445200")) {
            Name = "揭阳市";
        }else  if (Token.equals("445300")) {
            Name = "云浮市";
        }
        initMap();
        initSearch();
        lintener();
    }

    private void lintener() {
        equivalentLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//等外
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE=6;
                mPresenter.getSclerosis20("5", Token, MapActivity.this);
            }
        });
        modificationLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//砂石路
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE=5;
                mPresenter.getSclerosis20("4", Token, MapActivity.this);
            }
        });
        nobstructedLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//畅返不畅
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE=4;
                mPresenter.getSclerosis20("3", Token, MapActivity.this);
            }
        });
        sclerosis200Lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//200人
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE=3;
                mPresenter.getSclerosis20("1", Token, MapActivity.this);
            }
        });
        sclerosis20Lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//20户
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE = 2;
                mPresenter.getSclerosis20("2", Token, MapActivity.this);
            }
        });
        moresLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moresLay.getVisibility() == View.VISIBLE) {
                    moresLay.setVisibility(View.GONE);
                    menuLay.setVisibility(View.VISIBLE);
                }
            }
        });
        backLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        moreLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moresLay.setVisibility(View.VISIBLE);
                menuLay.setVisibility(View.GONE);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
            }
        });
        tunnelLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE = 1;
                mPresenter.getTunnel(Token, MapActivity.this);
            }
        });
        roadLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bridgeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateTe.setVisibility(View.GONE);
                timeLay.setVisibility(View.GONE);
                typeLay.setVisibility(View.GONE);
                obviousLay.setVisibility(View.GONE);
                aMap.clear();
                initSearch();
                TYPE = 0;
                mPresenter.getPoi(Token, MapActivity.this);
            }
        });
    }

    //画点
    @Override
    public void getPois(PoiBean poiBean) {
        obviousLay.setVisibility(View.VISIBLE);
        Glide.with(MapActivity.this).load(R.drawable.bridge_te_img).into(bridgeImg);
        bridgeName.setText("桥梁");
        bridgeWz.setText("桥梁总数:  " + poiBean.getZS() + "个");
        bridgeFl.setText("");
        qc.setText("总里程数:  " + poiBean.getLC() + "km");
        extralargelist.clear();
        largelist.clear();
        smalllist.clear();
        MultiPointOverlay smallOverlay = aMap.addMultiPointOverlay(smallOptions);
        MultiPointOverlay largeOverlay = aMap.addMultiPointOverlay(largeOptions);
        MultiPointOverlay extralargeOverlay = aMap.addMultiPointOverlay(extralargeOptions);
        for (int i = 0; i < poiBean.getDATA().size(); i++) {
            if (poiBean.getDATA().get(i).getKjfl().equals("特大桥")) {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                extralargelist.add(multiPointItem);
            } else if (poiBean.getDATA().get(i).getKjfl().equals("大桥")) {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                largelist.add(multiPointItem);
            } else {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                smalllist.add(multiPointItem);
            }
        }
        smallOverlay.setItems(smalllist);
        largeOverlay.setItems(largelist);
        extralargeOverlay.setItems(extralargelist);
    }

    @Override
    public void getPointClicks(List<PointClickBean.DATABean> ss) {
        bridgeName.setText(ss.get(0).getQlmc());
        bridgeWz.setText("桥梁位置:  " + ss.get(0).getLxbm() + ss.get(0).getZh());
        bridgeFl.setText("跨径分类:  " + ss.get(0).getKjfl());
        qc.setText("桥梁全长:  " + Utils.replaceNull(ss.get(0).getQc() + "") + "m");
    }

    //隧道
    @Override
    public void getTunnels(TunnelBean poiBean) {
        obviousLay.setVisibility(View.VISIBLE);
        Glide.with(MapActivity.this).load(R.drawable.tunnel_te_img).into(bridgeImg);
        bridgeName.setText("隧道");
        bridgeWz.setText("隧道总数:  " + poiBean.getZS() + "个");
        bridgeFl.setText("");
        qc.setText("总里程数:  " + poiBean.getLC() + "km");
        MultiPointOverlay smallOverlay = aMap.addMultiPointOverlay(smallOptions);
        MultiPointOverlay largeOverlay = aMap.addMultiPointOverlay(largeOptions);
        MultiPointOverlay extralargeOverlay = aMap.addMultiPointOverlay(extralargeOptions);
        extralargelist.clear();
        largelist.clear();
        smalllist.clear();
        for (int i = 0; i < poiBean.getDATA().size(); i++) {
            if (poiBean.getDATA().get(i).getKjfl().equals("长隧道")) {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                extralargelist.add(multiPointItem);
            } else if (poiBean.getDATA().get(i).getKjfl().equals("中隧道")) {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                largelist.add(multiPointItem);
            } else {
                LatLng latLng = new LatLng(poiBean.getDATA().get(i).getLat(), poiBean.getDATA().get(i).getLon(), false);
                MultiPointItem multiPointItem = new MultiPointItem(latLng);
                multiPointItem.setTitle(poiBean.getDATA().get(i).getGuid_obj() + "");
                smalllist.add(multiPointItem);
            }
        }
        smallOverlay.setItems(smalllist);
        largeOverlay.setItems(largelist);
        extralargeOverlay.setItems(extralargelist);
    }

    @Override
    public void getTunnelClicks(List<TunnelClickBean.DATABean> ss) {
        bridgeName.setText(ss.get(0).getSdmc());
        bridgeWz.setText("隧道位置:  " + ss.get(0).getLxbm() + ss.get(0).getZh());
        bridgeFl.setText("跨径分类:  " + ss.get(0).getKjfl());
        qc.setText("隧道全长:  " + Utils.replaceNull(ss.get(0).getSc() + "") + "m");
    }

    //20户
    @Override
    public void getSclerosis20s(Sclerosis20Bean sclerosis20Bean) {
        if (sclerosis20Bean.getDATA().size()>0){
            moresLay.setVisibility(View.GONE);
            menuLay.setVisibility(View.VISIBLE);
            obviousLay.setVisibility(View.VISIBLE);
            if (TYPE==2){
                Glide.with(MapActivity.this).load(R.drawable.sclerosis20_te_img).into(bridgeImg);
            }else if (TYPE==3){
                Glide.with(MapActivity.this).load(R.drawable.sclerosis200_te_img).into(bridgeImg);
            }else if (TYPE==4){
                Glide.with(MapActivity.this).load(R.drawable.nobstructed_te_img).into(bridgeImg);
            }else if (TYPE==5){
                Glide.with(MapActivity.this).load(R.drawable.modification_te_img).into(bridgeImg);
            }else if (TYPE==6){
                Glide.with(MapActivity.this).load(R.drawable.equivalent_te_img).into(bridgeImg);
            }
            bridgeName.setText(sclerosis20Bean.getDATA().get(0).getXmlx());
            bridgeWz.setText("统计范围:   " + Name);
            if (Token.equals("440000")) {
                bridgeFl.setText("全年计划:   " + sclerosis20Bean.getQNJH() + "公里");
            }else {
                bridgeFl.setText("全市计划:   " + sclerosis20Bean.getQNJH() + "公里");
            }
            qc.setText("");
            typeLay.setVisibility(View.VISIBLE);
            unstartedTe.setText(sclerosis20Bean.getWKG()+"公里");
            constructionTe.setText(sclerosis20Bean.getZJ()+"公里");
            completedTe.setText(sclerosis20Bean.getYGW()+"公里");
            extralargelist.clear();
            largelist.clear();
            smalllist.clear();
            MultiPointOverlay extralargeOverlay = aMap.addMultiPointOverlay(extralargeOptions);
            for (int i = 0; i < sclerosis20Bean.getDATA().size(); i++) {
                if (!Utils.isNull(sclerosis20Bean.getDATA().get(i).getLat() + "")) {
                    LatLng latLng = new LatLng(sclerosis20Bean.getDATA().get(i).getLat(), sclerosis20Bean.getDATA().get(i).getLon(), false);
                    MultiPointItem multiPointItem = new MultiPointItem(latLng);
                    multiPointItem.setTitle(sclerosis20Bean.getDATA().get(i).getId() + "");
                    extralargelist.add(multiPointItem);
                }
            }
            extralargeOverlay.setItems(extralargelist);
        }
    }

    @Override
    public void getSclerosis20Clicks(List<Sclerosis20Click.DATABean> ss) {
        if (ss.size()>0){
            typeLay.setVisibility(View.GONE);
            timeLay.setVisibility(View.VISIBLE);
            stateTe.setVisibility(View.VISIBLE);
            bridgeName.setText(ss.get(0).getXmmc());
            bridgeWz.setText("项目编号：   "+Utils.replaceNull(ss.get(0).getXmbm()));
            bridgeFl.setText("类型：   "+Utils.replaceNull(ss.get(0).getXmtype()));
            qc.setText("单位：   "+Utils.replaceNull(ss.get(0).getTbdwmc()));
            timeTe.setText("数据时间：   "+Utils.replaceNull(ss.get(0).getTbsj()));
            wctzTe.setText("完成投资：   "+Utils.replaceNull(ss.get(0).getLjwctz()+"")+"万元");
            stateTe.setText("工程状态：   "+Utils.replaceNull(ss.get(0).getXmjd()));
        }
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        smallOptions = new MultiPointOverlayOptions();
        smallOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bridge_small));
        smallOptions.anchor(0.125f, 0.125f);
        largeOptions = new MultiPointOverlayOptions();
        largeOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bridge_large));
        largeOptions.anchor(0.25f, 0.5f);
        extralargeOptions = new MultiPointOverlayOptions();
        extralargeOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bridge_extralarge));
        extralargeOptions.anchor(0.5f, 0.5f);
        // 定义海量点点击事件
        AMap.OnMultiPointClickListener multiPointClickListener = new AMap.OnMultiPointClickListener() {
            // 海量点中某一点被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onPointClick(MultiPointItem pointItem) {
                if (TYPE == 0) {
                    mPresenter.getPointClick(pointItem.getTitle(), MapActivity.this);
                } else if (TYPE == 1) {
                    mPresenter.getTunnelClick(pointItem.getTitle(), MapActivity.this);
                } else if (TYPE == 2) {
                    mPresenter.getSclerosis20Click(pointItem.getTitle(), MapActivity.this);
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(pointItem.getLatLng());
                LatLngBounds latLngBounds = builder.build();
                aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
                return false;
            }
        };
        // 绑定海量点点击事件
        aMap.setOnMultiPointClickListener(multiPointClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    //省份高亮
    private void initSearch() {
        DistrictSearch search = new DistrictSearch(MapActivity.this);
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords(Name);//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器
        search.searchDistrictAnsy();//开始搜索
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            PolylineOptions polylineOption = (PolylineOptions) msg.obj;
            aMap.addPolyline(polylineOption);

        }
    };

    @Override
    public void onDistrictSearched(DistrictResult districtResult) {
        if (districtResult == null || districtResult.getDistrict() == null) {
            return;
        }
        final DistrictItem item = districtResult.getDistrict().get(0);
        if (item == null) {
            return;
        }
        centerLatLng = item.getCenter();//得到行政中心点坐标
        if (centerLatLng != null) {  //地图加载时就显示行政区域
            if (getIntent().getStringExtra("Code").equals("440000")) {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(centerLatLng.getLatitude(), centerLatLng.getLongitude()), 8));//13为缩放级别
            } else {
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(centerLatLng.getLatitude(), centerLatLng.getLongitude()), 10));//13为缩放级别
            }

        }
        new Thread() {
            private PolylineOptions polylineOption;

            public void run() {
                String[] polyStr = item.districtBoundary();
                if (polyStr == null || polyStr.length == 0) {
                    return;
                }
                for (String str : polyStr) {
                    String[] lat = str.split(";");
                    polylineOption = new PolylineOptions();
                    boolean isFirst = true;
                    LatLng firstLatLng = null;
                    for (String latstr : lat) {
                        String[] lats = latstr.split(",");
                        if (isFirst) {
                            isFirst = false;
                            firstLatLng = new LatLng(Double
                                    .parseDouble(lats[1]), Double
                                    .parseDouble(lats[0]));
                        }
                        polylineOption.add(new LatLng(Double
                                .parseDouble(lats[1]), Double
                                .parseDouble(lats[0])));
                    }
                    if (firstLatLng != null) {
                        polylineOption.add(firstLatLng);
                    }

                    polylineOption.width(7).color(Color.BLUE);
                    Message message = handler.obtainMessage();
                    message.obj = polylineOption;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }
}

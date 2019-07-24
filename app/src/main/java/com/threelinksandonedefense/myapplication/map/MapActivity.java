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
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;

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
public class MapActivity extends MVPBaseActivity<MapContract.View, MapPresenter> implements MapContract.View, DistrictSearch.OnDistrictSearchListener, ClusterRender {
    @Bind(R.id.map)
    MapView mMapView;
    @Bind(R.id.lin)
    Button lin;
    @Bind(R.id.poi)
    Button poi;
    @Bind(R.id.pqi)
    Button pqi;
    private AMap aMap = null;
    private String Token = "CxEPsC3JLlrx4h78bLFjQMBo8pOVTJOJDIdXnaYEBlpJ81iXiIxymn4MrbQbITcg";
    private Map<Integer, Drawable> mBackDrawAbles = new HashMap<Integer, Drawable>();
    private LatLonPoint centerLatLng;
    private Polyline polyline;
    private ClusterOverlay mClusterOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        initMap();
        initSearch();
        lintener();
    }

    //省份高亮
    private void initSearch() {
        DistrictSearch search = new DistrictSearch(MapActivity.this);
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords("广东省");//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器
        search.searchDistrictAnsy();//开始搜索
    }

    private void lintener() {
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getData(Token, MapActivity.this);
            }
        });
        poi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getPoi(Token, MapActivity.this);
            }
        });
        pqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getPqi(Token, MapActivity.this);
            }
        });
    }

    //画线
    @Override
    public void getDatas(MdBean.RoadInfo s) {
        ArrayList<MdBean.LonLatsInfo> listdata = s.getRoad_lon_lat_province().get(0).getRoad_lon_lat_list();
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (int i = 0; i < listdata.size(); i++) {
            latLngs.add(new LatLng(Double.valueOf(listdata.get(i).getLatitude()), Double.valueOf(listdata.get(i).getLongitude())));
        }
        aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.RED));
    }

    //画点
    @Override
    public void getPois(PoiBean.ResultsBean ss) {
        List<ClusterItem> items = new ArrayList<ClusterItem>();
        for (int i = 0; i < ss.getTunnel_next_level().size(); i++) {
            LatLng latLng = new LatLng(Double.valueOf(ss.getTunnel_next_level().get(i).getLatitude()), Double.valueOf(ss.getTunnel_next_level().get(i).getLongitude()), false);
            RegionItem regionItem = new RegionItem(latLng,
                    "test" + i);
            items.add(regionItem);
        }
//        List<ClusterItem> items = new ArrayList<ClusterItem>();
//        //随机10000个点
//        for (int i = 0; i < 10000; i++) {
//            double lat = Math.random() + 39.474923;
//            double lon = Math.random() + 116.027116;
//            LatLng latLng = new LatLng(lat, lon, false);
//            RegionItem regionItem = new RegionItem(latLng,
//                    "test" + i);
//            items.add(regionItem);
//
//        }
        mClusterOverlay = new ClusterOverlay(aMap, items,
                dp2px(getApplicationContext(), 100),
                getApplicationContext());
        mClusterOverlay.setClusterRenderer(MapActivity.this);
    }

    @Override
    public void getPqis(PqiBean.PCIInfo sss) {
        ArrayList<PqiBean.RoadPCIInfo>  result =  sss.getRoad_indicator_list().get(0).getRoad_indicator_distribute();
        for (int i = 0; i < result.size(); i++) {
            ArrayList<PqiBean.LonLatsInfo> roadLonsLatsInfos = result.get(i).getRoad_lon_lat_list();
            int indicator_pci_grade = -1;
            indicator_pci_grade = Integer.valueOf(result.get(i).getIndicator_info().getIndicator_grade());
            int PCI_color = 0;
            switch (indicator_pci_grade) {
                case 1:
                    PCI_color = Color.rgb(120, 224, 57);
                    break;
                case 2:
                    PCI_color = Color.rgb(97, 251, 231);
                    break;
                case 3:
                    PCI_color = Color.rgb(224, 238, 115);
                    break;
                case 4:
                    PCI_color = Color.rgb(255, 170, 82);
                    break;
                case 5:
                    PCI_color = Color.rgb(250, 84, 2);
                    break;
                default:
                    PCI_color = Color.argb(127, 160, 160, 160);
                    break;
            }
            for (int j = 0; j < roadLonsLatsInfos.size(); j++) {
                List<LatLng> latLngs = new ArrayList<LatLng>();
                latLngs.clear();
                String s_longitude = roadLonsLatsInfos.get(j)
                        .getLongitude();
                String s_latitude = roadLonsLatsInfos.get(j)
                        .getLatitude();
                String s_longtitude_next = null;
                String s_latitude_next = null;
                if (roadLonsLatsInfos.get(j).getEnd_longitude() == null
                        && j < roadLonsLatsInfos.size() - 1) {

                    s_longtitude_next = roadLonsLatsInfos.get(j + 1)
                            .getLongitude();
                    s_latitude_next = roadLonsLatsInfos.get(j + 1)
                            .getLatitude();
                } else if (roadLonsLatsInfos.get(j).getEnd_longitude() != null) {

                    s_longtitude_next = roadLonsLatsInfos.get(j)
                            .getEnd_longitude();
                    s_latitude_next = roadLonsLatsInfos.get(j)
                            .getEnd_latitude();
                } else {

                }
                double longtitude = Double.valueOf(s_longitude);// 经度
                double latitude = Double.valueOf(s_latitude);// 纬度

                double longtitude_next = Double
                        .valueOf(s_longtitude_next);// 下一个点经度
                double latitude_next = Double.valueOf(s_latitude_next);// 下一个点经度
                latLngs.add(new LatLng(latitude,longtitude));
                latLngs.add(new LatLng(latitude_next,longtitude_next));
                aMap.addPolyline(new PolylineOptions().addAll(latLngs).width(10).color(PCI_color));
                Log.e( "张成昆: ", PCI_color+"");
            }
        }
    }
    private void initMap() {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
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
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(centerLatLng.getLatitude(), centerLatLng.getLongitude()), 7));//13为缩放级别
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

                    polylineOption.width(6).color(Color.BLUE);
                    Message message = handler.obtainMessage();
                    message.obj = polylineOption;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public Drawable getDrawAble(int clusterNum) {
        int radius = dp2px(getApplicationContext(), 80);
        if (clusterNum == 1) {
            Drawable bitmapDrawable = mBackDrawAbles.get(1);
            if (bitmapDrawable == null) {
                bitmapDrawable =
                        getApplication().getResources().getDrawable(
                                R.drawable.icon_openmap_mark);
                mBackDrawAbles.put(1, bitmapDrawable);
            }

            return bitmapDrawable;
        } else if (clusterNum < 5) {

            Drawable bitmapDrawable = mBackDrawAbles.get(2);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(159, 210, 154, 6)));
                mBackDrawAbles.put(2, bitmapDrawable);
            }

            return bitmapDrawable;
        } else if (clusterNum < 10) {
            Drawable bitmapDrawable = mBackDrawAbles.get(3);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(199, 217, 114, 0)));
                mBackDrawAbles.put(3, bitmapDrawable);
            }

            return bitmapDrawable;
        } else {
            Drawable bitmapDrawable = mBackDrawAbles.get(4);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.argb(235, 215, 66, 2)));
                mBackDrawAbles.put(4, bitmapDrawable);
            }

            return bitmapDrawable;
        }
    }

    private Bitmap drawCircle(int radius, int color) {

        Bitmap bitmap = Bitmap.createBitmap(radius * 2, radius * 2,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(0, 0, radius * 2, radius * 2);
        paint.setColor(color);
        canvas.drawArc(rectF, 0, 360, true, paint);
        return bitmap;
    }

    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }
}

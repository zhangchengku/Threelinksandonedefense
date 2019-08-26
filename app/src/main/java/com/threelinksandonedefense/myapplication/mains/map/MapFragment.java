package com.threelinksandonedefense.myapplication.mains.map;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.map.MapActivity;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseFragment;
import com.threelinksandonedefense.myapplication.selectcity.SelectCityActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MapFragment extends MVPBaseFragment<MapContract.View, MapPresenter> implements MapContract.View , DistrictSearch.OnDistrictSearchListener{
    @Bind(R.id.map)
    MapView mMapView;
    @Bind(R.id.fra_map_te)
    TextView fraMapTe;
    @Bind(R.id.fra_zc)
    ImageView fraZc;
    private AMap aMap;
    private String code = "440000";
    private LatLonPoint centerLatLng;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_map, null);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.setMyLocationStyle(myLocationStyle);
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        fraMapTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectCityActivity.class);
                startActivityForResult(intent, 9090);
            }
        });
        fraZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("Code",code);
                intent.putExtra("Name",fraMapTe.getText().toString());
                startActivity(intent);
            }
        });
        initSearch();
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9090&& resultCode == 9090) {
            code = (String) data.getSerializableExtra("CityCode");
            fraMapTe.setText((String) data.getSerializableExtra("CityName"));
            aMap.clear();
            initSearch();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //省份高亮
    private void initSearch() {
        DistrictSearch search = new DistrictSearch(getActivity());
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords(fraMapTe.getText().toString());//传入关键字
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
            if (code.equals("440000")) {
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
}

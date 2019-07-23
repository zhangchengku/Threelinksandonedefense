package com.threelinksandonedefense.myapplication.map;

import com.amap.api.maps.model.LatLng;

/**
 * Created by 张成昆 on 2019-7-17.
 */

public class RegionItem implements ClusterItem {
    private LatLng mLatLng;
    private String mTitle;
    public RegionItem(LatLng latLng, String title) {
        mLatLng=latLng;
        mTitle=title;
    }

    @Override
    public LatLng getPosition() {
        // TODO Auto-generated method stub
        return mLatLng;
    }
    public String getTitle(){
        return mTitle;
    }

}

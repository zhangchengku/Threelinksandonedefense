package com.threelinksandonedefense.myapplication.map;

import android.graphics.drawable.Drawable;

/**
 * Created by 张成昆 on 2019-7-17.
 */

public interface ClusterRender {
    /**
     * 根据聚合点的元素数目返回渲染背景样式
     *
     * @param clusterNum
     * @return
     */
    Drawable getDrawAble(int clusterNum);
}

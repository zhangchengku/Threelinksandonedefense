package com.threelinksandonedefense.myapplication;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.threelinksandonedefense.myapplication.utils.Utils;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by 张成昆 on 2019-7-3.
 */

public class ShowImgActivity extends AppCompatActivity  {
    @Bind(R.id.photo)
    ImageView photo;
    private String img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.show_img_layout);
        ButterKnife.bind(this);
        img2 = getIntent().getStringExtra("img2");
        initView();
    }

    /**
     * 实例化控件
     */
    private void initView() {
        Glide.with(ShowImgActivity.this)
                .load(img2)
                .into(photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

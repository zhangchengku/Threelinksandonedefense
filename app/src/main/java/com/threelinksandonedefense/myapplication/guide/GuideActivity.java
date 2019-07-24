package com.threelinksandonedefense.myapplication.guide;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.threelinksandonedefense.myapplication.MainActivity;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.Tadk;
import com.threelinksandonedefense.myapplication.circleoffriends.CircleoffriendsActivity;
import com.threelinksandonedefense.myapplication.completesectionfilling.CompleteSectionFillingActivity;
import com.threelinksandonedefense.myapplication.land.LandActivity;
import com.threelinksandonedefense.myapplication.map.MapActivity;
import com.threelinksandonedefense.myapplication.monthlyeport.MonthlyeportActivity;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.Utils;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GuideActivity extends MVPBaseActivity<GuideContract.View, GuidePresenter> implements GuideContract.View {
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        initdate();
    }
    private void initdate() {
        handler = new Handler();
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        } else {
            if (Utils.isNull(MyApplication.app.spUtils.getString("UserName", ""))){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(GuideActivity.this, LandActivity.class));
                        finish();
                    }
                }, 500);
            }else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(GuideActivity.this, MapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        }
    }
}

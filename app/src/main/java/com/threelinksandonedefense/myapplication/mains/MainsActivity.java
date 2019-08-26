package com.threelinksandonedefense.myapplication.mains;




import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mains.business.BusinessFragment;
import com.threelinksandonedefense.myapplication.mains.map.MapFragment;
import com.threelinksandonedefense.myapplication.mains.me.MeFragment;
import com.threelinksandonedefense.myapplication.mains.statistics.StatisticsFragment;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.xyz.tabitem.BottmTabItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainsActivity extends MVPBaseActivity<MainsContract.View, MainsPresenter> implements MainsContract.View {
    @Bind(R.id.main_map)
    BottmTabItem mainMap;
    @Bind(R.id.main_statistics)
    BottmTabItem mainStatistics;
    @Bind(R.id.main_business)
    BottmTabItem mainBusiness;
    @Bind(R.id.main_me)
    BottmTabItem mainMe;
    @Bind(R.id.buttom_tab)
    LinearLayout buttomTab;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.content)
    RelativeLayout content;
    private MapFragment mapFragment;
    private StatisticsFragment statisticsFragment;
    private BusinessFragment businessFragment;
    private MeFragment meFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        mapFragment = new MapFragment();
        statisticsFragment = new StatisticsFragment();
        businessFragment = new BusinessFragment();
        meFragment = new MeFragment();
        goToFragment(mapFragment);
    }
    private Fragment mContent = null;
    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }
    @OnClick({R.id.main_map, R.id.main_statistics, R.id.main_business, R.id.main_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_map:
                goToFragment(mapFragment);
                mainMap.setSelectState(true);
                mainStatistics.setSelectState(false);
                mainBusiness.setSelectState(false);
                mainMe.setSelectState(false);
                break;
            case R.id.main_statistics:
                goToFragment(statisticsFragment);
                mainMap.setSelectState(false);
                mainStatistics.setSelectState(true);
                mainBusiness.setSelectState(false);
                mainMe.setSelectState(false);
                break;
            case R.id.main_business:
                goToFragment(businessFragment);
                mainMap.setSelectState(false);
                mainStatistics.setSelectState(false);
                mainBusiness.setSelectState(true);
                mainMe.setSelectState(false);
                break;
            case R.id.main_me:
//                goToFragment(meFragment);
                break;
        }
    }
}


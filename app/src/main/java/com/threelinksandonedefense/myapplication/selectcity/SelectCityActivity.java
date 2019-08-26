package com.threelinksandonedefense.myapplication.selectcity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.NoScroolGridView;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectCityActivity extends MVPBaseActivity<SelectCityContract.View, SelectCityPresenter> implements SelectCityContract.View {
    @Bind(R.id.tv_city_quan)
    TextView tvCityQuan;
    @Bind(R.id.grid_city_all)
    NoScroolGridView gridCityAll;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.hea)
    RelativeLayout hea;
    public final static int code = 9090;
    private List<String> aaaa = new ArrayList<>();
    private List<String> latelyList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_select);
        ButterKnife.bind(this);
        title.setText("组织机构选择");
        latelyList.add("广州市");
        aaaa.add("440100");
        latelyList.add("韶关市");
        aaaa.add("440200");
        latelyList.add("深圳市");
        aaaa.add("440300");
        latelyList.add("珠海市");
        aaaa.add("440400");
        latelyList.add("汕头市");
        aaaa.add("440500");
        latelyList.add("佛山市");
        aaaa.add("440600");
        latelyList.add("江门市");
        aaaa.add("440700");
        latelyList.add("湛江市");
        aaaa.add("440800");
        latelyList.add("茂名市");
        aaaa.add("440900");
        latelyList.add("肇庆市");
        aaaa.add("441200");
        latelyList.add("惠州市");
        aaaa.add("441300");
        latelyList.add("梅州市");
        aaaa.add("441400");
        latelyList.add("汕尾市");
        aaaa.add("441500");
        latelyList.add("河源市");
        aaaa.add("441600");
        latelyList.add("阳江市");
        aaaa.add("441700");
        latelyList.add("清远市");
        aaaa.add("441800");
        latelyList.add("东莞市");
        aaaa.add("441900");
        latelyList.add("中山市");
        aaaa.add("442000");
        latelyList.add("潮州市");
        aaaa.add("445100");
        latelyList.add("揭阳市");
        aaaa.add("445200");
        latelyList.add("云浮市");
        aaaa.add("445300");
        tvCityQuan.getLayoutParams().width = (Utils.getWidth(this) - Utils.dip2px(this, 40)) / 3;
        gridCityAll.setAdapter(new GridViewAdapter(latelyList));
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvCityQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("CityName", "广东省");
                intent.putExtra("CityCode", "440000");
                setResult(code, intent);
                finish();
            }
        });
    }
    /**
     * 列表适配器
     *
     * @author WuXiaoHao
     *
     */
    private class GridViewAdapter extends BaseAdapter {
        private List<String> cityList;

        public GridViewAdapter(List<String> cityList) {
            this.cityList = cityList;
        }

        @Override
        public int getCount() {
            return cityList != null ? cityList.size() : 0;
        }

        @Override
        public String getItem(int arg0) {
            return cityList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(final int arg0, View arg1, ViewGroup arg2) {
            ViewHolder viewHolder;
            if (arg1 == null) {
                viewHolder = new ViewHolder();
                arg1 = LayoutInflater.from(SelectCityActivity.this).inflate(R.layout.act_select_item, arg2,
                        false);
                viewHolder.tv = (TextView) arg1.findViewById(R.id.tv_city_grid_item);
                arg1.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) arg1.getTag();
            }
            final String cityList = getItem(arg0);
            viewHolder.tv.setText(cityList);
            arg1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCity(arg0);
                }
            });
            return arg1;
        }

        private class ViewHolder {
            TextView tv;
        }

    }
    /**
     * 选择省份
     */
    private void selectCity(int arg0) {
        Intent intent = new Intent();
        intent.putExtra("CityName", latelyList.get(arg0));
        intent.putExtra("CityCode", aaaa.get(arg0));
        setResult(code, intent);
        finish();
    }
}

package com.threelinksandonedefense.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;
import com.threelinksandonedefense.myapplication.completesectionfilling.CompleteInitDataBean;
import com.threelinksandonedefense.myapplication.monthlyeport.MonthGridAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 张成昆 on 2019-6-24.
 */

public class Tadk extends AppCompatActivity {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.progress_te)
    TextView progressTe;
    @Bind(R.id.ed_progress)
    TextView edProgress;
    @Bind(R.id.rel_progress)
    RelativeLayout relProgress;
    @Bind(R.id.check_te)
    TextView checkTe;
    @Bind(R.id.ed_check)
    TextView edCheck;
    @Bind(R.id.rel_check)
    RelativeLayout relCheck;
    @Bind(R.id.evaluate_te)
    TextView evaluateTe;
    @Bind(R.id.ed_evaluate)
    TextView edEvaluate;
    @Bind(R.id.rel_evaluate)
    RelativeLayout relEvaluate;
    @Bind(R.id.completedbridge_te)
    TextView completedbridgeTe;
    @Bind(R.id.ed_completedbridge)
    EditText edCompletedbridge;
    @Bind(R.id.rel_completedbridge)
    RelativeLayout relCompletedbridge;
    @Bind(R.id.incompletebridge_te)
    TextView incompletebridgeTe;
    @Bind(R.id.ed_incompletebridge)
    EditText edIncompletebridge;
    @Bind(R.id.rel_incompletebridge)
    RelativeLayout relIncompletebridge;
    @Bind(R.id.listview)
    NoScroolGridView listview;
    @Bind(R.id.explain_et)
    EditText explainEt;
    @Bind(R.id.cai_ji_picture_add_grid)
    com.threelinksandonedefense.myapplication.utils.NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.tttt)
    TextView tttt;
    @Bind(R.id.location)
    LinearLayout location;
    @Bind(R.id.sx)
    TextView sx;
    @Bind(R.id.no_bridge_fw)
    RelativeLayout noBridgeFw;
    @Bind(R.id.is_bridge_fw)
    TextView isBridgeFw;
    @Bind(R.id.sssss)
    TextView sssss;
    @Bind(R.id.gps)
    RelativeLayout gps;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.lay_foot)
    LinearLayout layFoot;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;

    private ArrayList<String> mDatas = new ArrayList<>();
    private ListViewAdapter adapt;
    private MonthGridAdapter monthGridAdapter;
    private Map<Integer, MonthGridAdapter> mapAdapter = new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_task);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        adapt = new ListViewAdapter(this, mDatas);
        listview.setAdapter(adapt);
        title.setText("现场采集");
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.add);
        listPicture.add(addPicture);
        final int childPosition = layRel.getChildCount();
        monthGridAdapter = new MonthGridAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(monthGridAdapter);
        mapAdapter.put(childPosition, monthGridAdapter);
        OkGo.<String>get("http://106.37.229.146:4148/JLTH/InitZljy")
                .params("uid", "00000000-0000-0000-0000-000000000000")
                .execute(new StringDialogCallback(Tadk.this) {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        CompleteInitDataBean initDataBean = JSON.parseObject(response.body(),CompleteInitDataBean.class);
//                        if (mView == null)
//                            return;
//                        if (initDataBean.getSTATE().equals("1")){
//                            mView.getData(initDataBean.getDATA().get(0));
//                        }else {
//                            mView.onRequestError(initDataBean.getMSG());
//                        }
                    }
                });
    }
}

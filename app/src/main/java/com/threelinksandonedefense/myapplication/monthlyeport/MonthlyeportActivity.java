package com.threelinksandonedefense.myapplication.monthlyeport;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lxj.matisse.CaptureMode;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;
import com.threelinksandonedefense.myapplication.DictationResult;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.dialog.CommBtnListener;
import com.threelinksandonedefense.myapplication.dialog.CommNotificationDialog;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.NoScroolGridView;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MonthlyeportActivity extends MVPBaseActivity<MonthlyeportContract.View, MonthlyeportPresenter> implements MonthlyeportContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.item_number)
    TextView itemNumber;
    @Bind(R.id.district)
    TextView district;
    @Bind(R.id.company)
    TextView company;
    @Bind(R.id.month_te)
    TextView monthTe;
    @Bind(R.id.te_month)
    TextView teMonth;
    @Bind(R.id.rel_month)
    RelativeLayout relMonth;
    @Bind(R.id.time_te)
    TextView timeTe;
    @Bind(R.id.te_time)
    TextView teTime;
    @Bind(R.id.rel_time)
    RelativeLayout relTime;
    @Bind(R.id.filler_te)
    TextView fillerTe;
    @Bind(R.id.te_filler)
    TextView teFiller;
    @Bind(R.id.rel_filler)
    RelativeLayout relFiller;
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
    @Bind(R.id.switch1)
    Switch switch1;
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
    @Bind(R.id.completedsquare_te)
    TextView completedsquareTe;
    @Bind(R.id.ed_completedsquare)
    EditText edCompletedsquare;
    @Bind(R.id.rel_completedsquare)
    RelativeLayout relCompletedsquare;
    @Bind(R.id.incompletesquare_te)
    TextView incompletesquareTe;
    @Bind(R.id.ed_incompletesquare)
    EditText edIncompletesquare;
    @Bind(R.id.rel_incompletesquare)
    RelativeLayout relIncompletesquare;
    @Bind(R.id.monthinvestment_te)
    TextView monthinvestmentTe;
    @Bind(R.id.ed_monthinvestment)
    EditText edMonthinvestment;
    @Bind(R.id.rel_monthinvestment)
    RelativeLayout relMonthinvestment;
    @Bind(R.id.funds_te)
    TextView fundsTe;
    @Bind(R.id.ed_funds)
    TextView edFunds;
    @Bind(R.id.rel_funds)
    RelativeLayout relFunds;
    @Bind(R.id.centralinvestment_te)
    TextView centralinvestmentTe;
    @Bind(R.id.ed_centralinvestment)
    EditText edCentralinvestment;
    @Bind(R.id.rel_centralinvestment)
    RelativeLayout relCentralinvestment;
    @Bind(R.id.provincialInvestment_te)
    TextView provincialInvestmentTe;
    @Bind(R.id.ed_provincialInvestment)
    EditText edProvincialInvestment;
    @Bind(R.id.rel_provincialInvestment)
    RelativeLayout relProvincialInvestment;
    @Bind(R.id.citymatching_te)
    TextView citymatchingTe;
    @Bind(R.id.ed_citymatching)
    EditText edCitymatching;
    @Bind(R.id.rel_citymatching)
    RelativeLayout relCitymatching;
    @Bind(R.id.countymatching_te)
    TextView countymatchingTe;
    @Bind(R.id.ed_countymatching)
    EditText edCountymatching;
    @Bind(R.id.rel_countymatching)
    RelativeLayout relCountymatching;
    @Bind(R.id.villagematching_te)
    TextView villagematchingTe;
    @Bind(R.id.ed_villagematching)
    EditText edVillagematching;
    @Bind(R.id.rel_villagematching)
    RelativeLayout relVillagematching;
    @Bind(R.id.investment_te)
    TextView investmentTe;
    @Bind(R.id.ed_investment)
    TextView edInvestment;
    @Bind(R.id.rel_investment)
    RelativeLayout relInvestment;
    @Bind(R.id.centralinvestment2_te)
    TextView centralinvestment2Te;
    @Bind(R.id.ed_centralinvestment2)
    EditText edCentralinvestment2;
    @Bind(R.id.rel_centralinvestment2)
    RelativeLayout relCentralinvestment2;
    @Bind(R.id.provincialinvestment2_te)
    TextView provincialinvestment2Te;
    @Bind(R.id.ed_provincialinvestment2)
    EditText edProvincialinvestment2;
    @Bind(R.id.rel_provincialinvestment2)
    RelativeLayout relProvincialinvestment2;
    @Bind(R.id.citymatching2_te)
    TextView citymatching2Te;
    @Bind(R.id.ed_citymatching2)
    EditText edCitymatching2;
    @Bind(R.id.rel_citymatching2)
    RelativeLayout relCitymatching2;
    @Bind(R.id.countymatching2_te)
    TextView countymatching2Te;
    @Bind(R.id.ed_countymatching2)
    EditText edCountymatching2;
    @Bind(R.id.rel_countymatching2)
    RelativeLayout relCountymatching2;
    @Bind(R.id.villagematching2_te)
    TextView villagematching2Te;
    @Bind(R.id.ed_villagematching2)
    EditText edVillagematching2;
    @Bind(R.id.rel_villagematching2)
    RelativeLayout relVillagematching2;
    @Bind(R.id.explain_et)
    EditText explainEt;
    @Bind(R.id.information_et)
    EditText informationEt;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.switch2)
    Switch switch2;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    @Bind(R.id.handle_img)
    ImageView handleimg;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.delete)
    ImageView delete;
    @Bind(R.id.gcjz_bu)
    RelativeLayout gcjzBu;
    @Bind(R.id.dwwc_bu)
    RelativeLayout dwwcBu;
    private int REQUEST_CODE_CHOOSE = 66;
    private int childViewPosition;//点击拍照获取的childveiw下标
    private MonthGridAdapter monthGridAdapter;
    private Map<Integer, MonthGridAdapter> mapAdapter = new HashMap<>();
    private OptionsPickerView progressCustomOptions;
    private List<String> progressItems = new ArrayList<>();
    private List<String> checkItems = new ArrayList<>();
    private List<String> evaluateItems = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat;
    private String Pic = "";
    private InitDataBean.DATABean InitDataBeans;
    private CommNotificationDialog UpadDialog;
    private MinePopupWindow minePopupWindow;
    private static String APPID = "5bf211f5";
    private String dictationResultStr;
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private String PicData = "";
    private String Ljdwzj;
    private String Ljwctz;

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private String Sfmsxm = "是";
    private String Issync = "1";
    private int REQUEST_CODE = 88;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_monthlyeport);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        mPresenter.initdata(getIntent().getStringExtra("xmid"), MonthlyeportActivity.this);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Sfmsxm = "是";
                } else {
                    Sfmsxm = "否";
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Issync = "1";
                } else {
                    Issync = "0";
                }
            }
        });
    }

    private void initview() {
        title.setText(Utils.replaceNull(getIntent().getStringExtra("name")));
        checkItems.add("是");
        checkItems.add("否");
        evaluateItems.add("未评定");
        evaluateItems.add("优");
        evaluateItems.add("良");
        evaluateItems.add("合格");
        evaluateItems.add("不合格");
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.add);
        listPicture.add(addPicture);
        final int childPosition = layRel.getChildCount();
        monthGridAdapter = new MonthGridAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(monthGridAdapter);
        mapAdapter.put(childPosition, monthGridAdapter);
        edCentralinvestment.addTextChangedListener(textWatcher);
        edProvincialInvestment.addTextChangedListener(textWatcher);
        edCitymatching.addTextChangedListener(textWatcher);
        edCountymatching.addTextChangedListener(textWatcher);
        edVillagematching.addTextChangedListener(textWatcher);
        edCentralinvestment2.addTextChangedListener(textWatcher2);
        edProvincialinvestment2.addTextChangedListener(textWatcher2);
        edCitymatching2.addTextChangedListener(textWatcher2);
        edCountymatching2.addTextChangedListener(textWatcher2);
        edVillagematching2.addTextChangedListener(textWatcher2);
        int width = (Utils.getWidth(MonthlyeportActivity.this) - Utils.dip2px(MonthlyeportActivity.this, 10) * 3) / 4;
        LayoutParams params = new LayoutParams(width, width);
        handleimg.setLayoutParams(params);
    }

    @OnClick({R.id.ed_progress, R.id.ed_check, R.id.ed_evaluate, R.id.rel_time, R.id.handle_img, R.id.add, R.id.dwwc_bu, R.id.gcjz_bu, R.id.go_back, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_progress:
                setOption(progressItems, edProgress);
                break;
            case R.id.go_back:
                finish();
                break;
            case R.id.ed_check:
                setOption(checkItems, edCheck);
                break;
            case R.id.ed_evaluate:
                setOption(evaluateItems, edEvaluate);
                break;
            case R.id.rel_time:
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(MonthlyeportActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        teTime.setText(getTime(date));
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.handle_img:
                if (Pic.equals("")) {
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(MonthlyeportActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(layRel, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.add:
                List<String> titles = new ArrayList<>();
                if (Float.valueOf(Utils.rerurn0(edFunds.getText().toString())) < Float.valueOf(Utils.rerurn0(Ljdwzj))) {
                    titles.add("累计到位资金额度小于上月，确认无误？");
                }
                if (Float.valueOf(Utils.rerurn0(edInvestment.getText().toString())) < Float.valueOf(Utils.rerurn0(Utils.rerurn0(Ljwctz)))) {
                    titles.add("累计完成资金额度小于上月，确认无误？");
                }
                if (Float.valueOf(Utils.rerurn0(edFunds.getText().toString())) < Float.valueOf(Utils.rerurn0(edInvestment.getText().toString()))) {
                    titles.add("完成资金大于到位资金，确认无误？");
                }
                isCan(titles);
                break;
            case R.id.dwwc_bu:
                getKdxf(informationEt);
                break;
            case R.id.gcjz_bu:
                getKdxf(explainEt);
                break;
            case R.id.delete:
                Glide.with(this).load(R.drawable.add).into(handleimg);
                delete.setVisibility(View.GONE);
                Pic = "";
                break;
        }
    }

    /**
     * 自定义pop监听
     */
    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mine_camera_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getFromCamera();
                    break;
                case R.id.mine_photo_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getFromPhoto();
                    break;
                case R.id.mine_cancel_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 从相册获取
     */
    public void getFromPhoto() {
        Matisse.from(MonthlyeportActivity.this)
                .choose(MimeType.ofAll()) //显示所有文件类型，比如图片和视频，
                .isCrop(true)//开启裁剪，默认不开启
                .maxSelectable(1)
                .forResult(REQUEST_CODE); //请求码
    }

    /**
     * 照相获取
     */
    public void getFromCamera() {
        Matisse.from(MonthlyeportActivity.this)
                .jumpCapture(CaptureMode.Image)//只拍照片
                .isCrop(true)
                .forResult(REQUEST_CODE);
    }

    private void addData() {
        SaveJson saveJson = new SaveJson();
        saveJson.setXmid(getIntent().getStringExtra("xmid"));
        saveJson.setTbyf(teMonth.getText().toString());
        saveJson.setTbr(teFiller.getText().toString());
        saveJson.setYjwcsj(teTime.getText().toString());
        saveJson.setTbdwdm(MyApplication.spUtils.getString("UnitNo"));
        saveJson.setTbdwmc(MyApplication.spUtils.getString("UnitName"));
        if (edProgress.getText().toString().equals("已完工")){
            saveJson.setSfys(edCheck.getText().toString());//是否验收
            saveJson.setZlpd(edEvaluate.getText().toString());//质量评定
        }else {
            saveJson.setSfys("");
            saveJson.setZlpd("");
        }
        saveJson.setXmjd(edProgress.getText().toString());
        saveJson.setSfmsxm(Sfmsxm);
        saveJson.setWcqlgs(Utils.rerurn0(edCompletedbridge.getText().toString()));
        saveJson.setWwcqlgs(Utils.rerurn0(edIncompletebridge.getText().toString()));
        saveJson.setWcpfm(Utils.rerurn0(edCompletedsquare.getText().toString()));
        saveJson.setWwcpfm(Utils.rerurn0(edIncompletesquare.getText().toString()));
        saveJson.setDywctz(Utils.rerurn0(edMonthinvestment.getText().toString()));
        saveJson.setLjdwzj(Utils.rerurn0(edFunds.getText().toString()));//累计到位资金

        saveJson.setLjdwzyzj(Utils.rerurn0(edCentralinvestment.getText().toString()));
        saveJson.setLjdwstz(Utils.rerurn0(edProvincialInvestment.getText().toString()));
        saveJson.setLjdwsjpt(Utils.rerurn0(edCitymatching.getText().toString()));
        saveJson.setLjdwxjpt(Utils.rerurn0(edCountymatching.getText().toString()));
        saveJson.setLjdwczjpt(Utils.rerurn0(edVillagematching.getText().toString()));

        saveJson.setLjwctz(Utils.rerurn0(edInvestment.getText().toString()));//累计完成资金
        saveJson.setLjwczytz(Utils.rerurn0(edCentralinvestment2.getText().toString()));
        saveJson.setLjwcstz(Utils.rerurn0(edProvincialinvestment2.getText().toString()));
        saveJson.setLjwcsjpt(Utils.rerurn0(edCitymatching2.getText().toString()));
        saveJson.setLjwcxjpt(Utils.rerurn0(edCountymatching2.getText().toString()));
        saveJson.setLjwcczjpt(Utils.rerurn0(edVillagematching2.getText().toString()));
        saveJson.setGcjzsm(Utils.replaceNull(explainEt.getText().toString()));
        saveJson.setDywcxx(Utils.replaceNull(informationEt.getText().toString()));
        saveJson.setIssync(Issync);
        saveJson.setUserid(MyApplication.spUtils.getString("UserID"));
        ArrayList<String> listPic = new ArrayList<>();
        if (monthGridAdapter.getListImgUrl().size() > 0) {
            for (int i = 0; i < monthGridAdapter.getListImgUrl().size(); i++) {
                listPic.add(Utils.bmpToBase64String(monthGridAdapter.getListImgUrl().get(i)));
            }
        }
        saveJson.setPicList(listPic);
        saveJson.setPicUrl("");
        String json = JSON.toJSONString(saveJson);
        mPresenter.SaveJdybInfo(json, MonthlyeportActivity.this);
    }

    private double edCentralinvestments2, edProvincialInvestments2, edCitymatchings2, edCountymatchings2, edVillagematchings2;
    private TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (Utils.isNull(edCentralinvestment2.getText().toString())) {
                edCentralinvestments2 = 0.0;
            } else {
                edCentralinvestments2 = Double.valueOf(edCentralinvestment2.getText().toString());
            }

            if (Utils.isNull(edProvincialinvestment2.getText().toString())) {
                edProvincialInvestments2 = 0.0;
            } else {
                edProvincialInvestments2 = Double.valueOf(edProvincialinvestment2.getText().toString());
            }
            if (Utils.isNull(edCitymatching2.getText().toString())) {
                edCitymatchings2 = 0;
            } else {
                edCitymatchings2 = Double.valueOf(edCitymatching2.getText().toString());
            }
            if (Utils.isNull(edCountymatching2.getText().toString())) {
                edCountymatchings2 = 0.0;
            } else {
                edCountymatchings2 = Double.valueOf(edCountymatching2.getText().toString());
            }
            if (Utils.isNull(edVillagematching2.getText().toString())) {
                edVillagematchings2 = 0.0;
            } else {
                edVillagematchings2 = Double.valueOf(edVillagematching2.getText().toString());
            }
            edInvestment.setText(edCentralinvestments2 + edProvincialInvestments2 + edCitymatchings2 + edCountymatchings2 + edVillagematchings2 + "");
        }
    };
    private double edCentralinvestments, edProvincialInvestments, edCitymatchings, edCountymatchings, edVillagematchings;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (Utils.isNull(edCentralinvestment.getText().toString())) {
                edCentralinvestments = 0.0;
            } else {
                edCentralinvestments = Double.valueOf(edCentralinvestment.getText().toString());
            }
            if (Utils.isNull(edProvincialInvestment.getText().toString())) {
                edProvincialInvestments = 0.0;
            } else {
                edProvincialInvestments = Double.valueOf(edProvincialInvestment.getText().toString());
            }
            if (Utils.isNull(edCitymatching.getText().toString())) {
                edCitymatchings = 0.0;
            } else {
                edCitymatchings = Double.valueOf(edCitymatching.getText().toString());
            }
            if (Utils.isNull(edCountymatching.getText().toString())) {
                edCountymatchings = 0.0;
            } else {
                edCountymatchings = Double.valueOf(edCountymatching.getText().toString());
            }
            if (Utils.isNull(edVillagematching.getText().toString())) {
                edVillagematchings = 0.0;
            } else {
                edVillagematchings = Double.valueOf(edVillagematching.getText().toString());
            }
            edFunds.setText(edCentralinvestments + edProvincialInvestments + edCitymatchings + edCountymatchings + edVillagematchings + "");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final MonthGridAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
            final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
            final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
            String imgPath = Matisse.obtainCropResult(data);
            listPicture.remove(listPicture.size() - 1);
            Bitmap bitmap = Utils.getImageThumbnail(imgPath, Utils.dip2px(MonthlyeportActivity.this, 480),
                    Utils.dip2px(MonthlyeportActivity.this, 480));
            if (bitmap != null) {
                Drawable drawable = new BitmapDrawable(bitmap);
                listPicture.add(drawable);
                listImgUrl.add(imgPath);
                Drawable addPicture = getResources().getDrawable(R.drawable.add);
                listPicture.add(addPicture);
                addPictureAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (!Utils.isNull(Matisse.obtainCropResult(data))) {
                PicData = Matisse.obtainCropResult(data);
                Glide.with(this).load(Matisse.obtainCropResult(data)).into(handleimg);
                delete.setVisibility(View.VISIBLE);
            }
        }
    }

    private String getTime(Date date) {
        return simpleDateFormat.format(date);
    }

    public void setOption(final List<String> datas, final TextView textview) {
        progressCustomOptions = new OptionsPickerView.Builder(MonthlyeportActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = datas.get(options1);
                if (tx.equals("已完工")) {
                    relCheck.setVisibility(View.VISIBLE);
                    relEvaluate.setVisibility(View.VISIBLE);
                }else if (tx.equals("在建")){
                    relCheck.setVisibility(View.GONE);
                    relEvaluate.setVisibility(View.GONE);
                }else if (tx.equals("交竣工")){
                    relCheck.setVisibility(View.GONE);
                    relEvaluate.setVisibility(View.GONE);
                }else if (tx.equals("未开工")){
                    relCheck.setVisibility(View.GONE);
                    relEvaluate.setVisibility(View.GONE);
                }
                textview.setText(tx);
            }
        }).setLayoutRes(R.layout.progress_lay, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressCustomOptions.returnData();
                        progressCustomOptions.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressCustomOptions.dismiss();
                    }
                });
            }
        })

                .build();
        progressCustomOptions.setPicker(datas);
        progressCustomOptions.show();
    }

    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    public void getData(InitDataBean DataBean) {
        if (DataBean.getDATA().size()>0){
            InitDataBean.DATABean initDataBean = DataBean.getDATA().get(0);
            if (!Utils.isNull(initDataBean.getXmtbtype())) {
                if (initDataBean.getXmtbtype().equals("2")) {
                    relCompletedbridge.setVisibility(View.VISIBLE);
                    relIncompletebridge.setVisibility(View.VISIBLE);
                } else {
                    relCompletedsquare.setVisibility(View.VISIBLE);
                    relIncompletesquare.setVisibility(View.VISIBLE);
                }
            }
            InitDataBeans = initDataBean;
            itemNumber.setText(Utils.replaceNull(initDataBean.getXmbm()));
            district.setText(Utils.replaceNull(initDataBean.getSzxzqh()));
            company.setText(Utils.replaceNull(initDataBean.getTbdwmc()));
            if (!Utils.isNull(initDataBean.getXmimage())) {
                Glide.with(this).load(initDataBean.getXmimage()).into(handleimg);
                Pic = initDataBean.getXmimage();
                delete.setVisibility(View.VISIBLE);
            }
            Calendar calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = simpleDateFormat.format(calendar.getTime());
            teMonth.setText(currentDate);//默认日期
            if (!Utils.isNull(initDataBean.getYjwcsj())) {
                teTime.setText(initDataBean.getYjwcsj());
            } else {
                teTime.setText(currentDate);//默认日期
            }
            teFiller.setText(Utils.replaceNull(MyApplication.spUtils.getString("UserName")));
            if (!Utils.isNull(initDataBean.getSfys())) {
                edCheck.setText(initDataBean.getSfys());
            } else {
                edCheck.setText("是");
            }
            if (!Utils.isNull(initDataBean.getZlpd())) {
                edEvaluate.setText(initDataBean.getZlpd());
            } else {
                edEvaluate.setText("未评定");
            }
            if (!Utils.isNull(initDataBean.getWcqlgs())) {
                edCompletedbridge.setText(initDataBean.getWcqlgs());
            }
            if (!Utils.isNull(initDataBean.getWwcqlgs())) {
                edIncompletebridge.setText(initDataBean.getWwcqlgs());
            }
            if (!Utils.isNull(initDataBean.getWcpfm())) {
                edCompletedsquare.setText(initDataBean.getWcpfm());
            }
            if (!Utils.isNull(initDataBean.getWwcpfm())) {
                edIncompletesquare.setText(initDataBean.getWwcpfm());
            }
            if (!Utils.isNull(initDataBean.getDywctz())) {
                edMonthinvestment.setText(initDataBean.getDywctz());
            }
            if (!Utils.isNull(initDataBean.getLjdwzj())) {//累计到位资金
                edFunds.setText(initDataBean.getLjdwzj());
                Ljdwzj = initDataBean.getLjdwzj();
            }
            if (!Utils.isNull(initDataBean.getLjdwzyzj())) {//中央投资
                edCentralinvestment.setText(initDataBean.getLjdwzyzj());
            }
            if (!Utils.isNull(initDataBean.getLjdwstz())) {//省
                edProvincialInvestment.setText(initDataBean.getLjdwstz());
            }
            if (!Utils.isNull(initDataBean.getLjdwsjpt())) {//市
                edCitymatching.setText(initDataBean.getLjdwsjpt());
            }
            if (!Utils.isNull(initDataBean.getLjdwxjpt())) {//县
                edCountymatching.setText(initDataBean.getLjdwxjpt());
            }
            if (!Utils.isNull(initDataBean.getLjdwczjpt())) {//村
                edVillagematching.setText(initDataBean.getLjdwczjpt());
            }
            if (!Utils.isNull(initDataBean.getLjwctz())) {//累计完成资金
                edInvestment.setText(initDataBean.getLjwctz());
                Ljwctz = initDataBean.getLjwctz();
            }
            if (!Utils.isNull(initDataBean.getLjwczytz())) {//中央
                edCentralinvestment2.setText(initDataBean.getLjwczytz());
            }
            if (!Utils.isNull(initDataBean.getLjwcstz())) {//省
                edProvincialinvestment2.setText(initDataBean.getLjwcstz());
            }
            if (!Utils.isNull(initDataBean.getLjwcsjpt())) {//市
                edCitymatching2.setText(initDataBean.getLjwcsjpt());
            }
            if (!Utils.isNull(initDataBean.getLjwcxjpt())) {//县
                edCountymatching2.setText(initDataBean.getLjwcxjpt());
            }
            if (!Utils.isNull(initDataBean.getLjwcczjpt())) {//市
                edVillagematching2.setText(initDataBean.getLjwcczjpt());
            }
            if (DataBean.getCOMDATA().size() > 0) {
                for (int i = 0; i < DataBean.getCOMDATA().size(); i++) {
                    progressItems.add(DataBean.getCOMDATA().get(i).getZdName());
                    if (i == 0) {
                        edProgress.setText(DataBean.getCOMDATA().get(i).getZdName());
                    }
                }
                if (DataBean.getCOMDATA().get(0).getZdName().equals("已完工")) {
                    relCheck.setVisibility(View.VISIBLE);
                    relEvaluate.setVisibility(View.VISIBLE);
                }
            }
            if (!Utils.isNull(initDataBean.getSfmsxm())) {
                if (initDataBean.getSfmsxm().equals("是")) {
                    switch1.setChecked(true);
                    Sfmsxm = "是";
                } else {
                    switch1.setChecked(false);
                    Sfmsxm = "否";
                }
            }
            if (!Utils.isNull(initDataBean.getGcjzsm())) {
                explainEt.setText(initDataBean.getGcjzsm());
            }
            if (!Utils.isNull(initDataBean.getDywcxx())) {
                informationEt.setText(initDataBean.getDywcxx());
            }
            if (!Utils.isNull(initDataBean.getIssync())) {
                if (initDataBean.getIssync().equals("1")) {
                    switch2.setChecked(true);
                    Issync = "1";
                } else {
                    switch2.setChecked(false);
                    Issync = "0";
                }
            }
            initview();
        }

    }

    @Override
    public void getData2() {
        if (!Utils.isNull(PicData)) {
            List<String> picBean = new ArrayList<>();
            picBean.add(Utils.bmpToBase64String(PicData));
            AddPicJson AddPicJson = new AddPicJson();
            AddPicJson.setXmid(getIntent().getStringExtra("xmid"));
            AddPicJson.setPicList(picBean);
            String json = JSON.toJSONString(AddPicJson);
            mPresenter.addPic(json, MonthlyeportActivity.this);
        } else {
            ToastUtils.showShortToast("上传成功");
            Intent intent = new Intent();
            setResult(666, intent);
            finish();
        }

    }

    @Override
    public void getData3() {
        ToastUtils.showShortToast("上传成功");
        Intent intent = new Intent();
        setResult(666, intent);
        finish();
    }

    public void isCan(List<String> titles) {
        String titleString = "";
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                titleString += titles.get(i);
            } else {
                titleString += "\n" + titles.get(i);
            }
        }
        if (titles.size() == 0) {
            titleString = "是否提交？";
        }
        if (UpadDialog == null) {
            String okStr = "确定";
            String cancelStr = "取消";
            UpadDialog = new CommNotificationDialog(MonthlyeportActivity.this, titleString, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    addData();
                }

                @Override
                public void CommCancelBtnClick() {
                    UpadDialog.close();
                }
            });
        } else {
            UpadDialog.setWarmTitle(titleString);
        }
        UpadDialog.show();
    }

    private void getKdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(MonthlyeportActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(MonthlyeportActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(MonthlyeportActivity.this, null);
        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 3.开始听写
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                if (!isLast) {
                    dictationResultStr += results.getResultString() + ",";
                } else {
                    dictationResultStr += results.getResultString() + "]";
                }
                if (isLast) {
                    List<DictationResult> dictationResultList = JSON.parseObject(dictationResultStr, new TypeReference<List<DictationResult>>() {
                    }.getType());
                    String finalResult = "";
                    for (int i = 0; i < dictationResultList.size() - 1; i++) {
                        finalResult += dictationResultList.get(i).toString();
                    }
                    xf.clear();
                    zong.clear();
                    zong.add(kdaxfdate.getText().toString());
                    zong.add(finalResult);
                    kdaxfdate.setText(ToString(zong));
                    kdaxfdate.requestFocus();
                    kdaxfdate.setSelection(ToString(zong).length());
                }
            }

            @Override
            public void onError(SpeechError error) {
                error.getPlainDescription(true);
            }

        });
        iatDialog.show();
    }

    public static String ToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }
}


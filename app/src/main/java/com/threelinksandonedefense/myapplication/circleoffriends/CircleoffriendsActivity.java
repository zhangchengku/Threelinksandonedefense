package com.threelinksandonedefense.myapplication.circleoffriends;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.TimePickerView;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lxj.matisse.Matisse;
import com.threelinksandonedefense.myapplication.DictationResult;

import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;

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
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CircleoffriendsActivity extends MVPBaseActivity<CircleoffriendsContract.View, CircleoffriendsPresenter> implements CircleoffriendsContract.View {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private static String APPID = "5bf211f5";
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.hea)
    RelativeLayout hea;
    @Bind(R.id.road_name)
    TextView roadName;
    @Bind(R.id.road_edit)
    EditText roadEdit;
    @Bind(R.id.yuyin)
    ImageView yuyin;
    @Bind(R.id.rrrrr)
    RelativeLayout rrrrr;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_new_bh_content_layou)
    LinearLayout diseaseNewBhContentLayou;
    @Bind(R.id.header)
    RelativeLayout header;
    @Bind(R.id.rq_te)
    TextView rqTe;
    @Bind(R.id.time_layout)
    LinearLayout timeLayout;
    @Bind(R.id.sgdw_te)
    TextView sgdwTe;
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
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.foord)
    LinearLayout foord;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.bg_layout)
    RelativeLayout bgLayout;
    private String dictationResultStr;
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat;
    private CircleoffriendsGridAdapter circleoffriendsGridAdapter;
    private Map<Integer, CircleoffriendsGridAdapter> mapAdapter = new HashMap<>();
    private String ID = UUID.randomUUID().toString();
    private int GETFRIEND = 666;

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private int childViewPosition;//点击拍照获取的childveiw下标
    private int REQUEST_CODE_CHOOSE = 66;
    private String TYPE="";
    private double Latitude,Longitude;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.halving_line);
        setContentView(R.layout.act_new);
        ButterKnife.bind(this);
        initview();
    }
    private void initview() {
        title.setText("发布动态");
        sgdwTe.setText(MyApplication.spUtils.getString("UnitName"));
        hea.setBackgroundColor(getResources().getColor(R.color.halving_line));
        roadName.setText(getIntent().getStringExtra("name"));
        Calendar calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        rqTe.setText(currentDate);//默认日期
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<VideoBean> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.add);
        listPicture.add(addPicture);
        final int childPosition = diseaseNewBhContentLayou.getChildCount();
        circleoffriendsGridAdapter = new CircleoffriendsGridAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(circleoffriendsGridAdapter);
        mapAdapter.put(childPosition, circleoffriendsGridAdapter);
        if (mLocationClient == null) {
            initLocation();
        }
        startLocation();
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationCacheEnable(false);
        mLocationClient.setLocationOption(mLocationOption);
    }

    private void startLocation() {
        mLocationClient.startLocation();
    }

    private void stopLocation() {
        mLocationClient.stopLocation();
    }

    private void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }


    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                isBridgeFw.setVisibility(View.VISIBLE);
                isBridgeFw.setText(aMapLocation.getAddress());
                Latitude = aMapLocation.getLatitude();
                Longitude=aMapLocation.getLongitude();
                Log.e("张成昆 ", aMapLocation.getAddress() + "===============" + aMapLocation.getLongitude() + "");
            } else {
                stopLocation();
            }
        }
    };

    private void getKdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(CircleoffriendsActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(CircleoffriendsActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(CircleoffriendsActivity.this, null);
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
                        finalResult += dictationResultList.get(i)
                                .toString();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final CircleoffriendsGridAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
            final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
            final ArrayList<VideoBean> listImgUrl = addPictureAdapter.getListImgUrl();
            VideoBean videoBean = new VideoBean();
            String imgPath;
            if (Utils.isNull(Matisse.obtainCaptureImageResult(data))){
                imgPath = Matisse.obtainCropResult(data);
                videoBean.setPic(imgPath);
            }else {
                imgPath =  Matisse.obtainCaptureImageResult(data);
                videoBean.setPic(imgPath);
                videoBean.setVideo(Matisse.obtainCaptureVideoResult(data));
            }
            listPicture.remove(listPicture.size() - 1);
            Bitmap bitmap = Utils.getImageThumbnail(imgPath, Utils.dip2px(CircleoffriendsActivity.this, 480),
                    Utils.dip2px(CircleoffriendsActivity.this, 480));
            if (bitmap != null) {
                Drawable drawable = new BitmapDrawable(bitmap);
                listPicture.add(drawable);
                listImgUrl.add(videoBean);
                Drawable addPicture = getResources().getDrawable(R.drawable.add);
                listPicture.add(addPicture);
                addPictureAdapter.notifyDataSetChanged();
            }
        }
    }
    @OnClick({R.id.go_back, R.id.yuyin, R.id.rq_te, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.yuyin:
                getKdxf(roadEdit);
                break;
            case R.id.rq_te:
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(CircleoffriendsActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        rqTe.setText(simpleDateFormat.format(date));
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.add:
                getTYPE();
                FriendAddJson friendAddJson = new  FriendAddJson();
                friendAddJson.setID(ID);
                friendAddJson.setXmId(getIntent().getStringExtra("code"));
                friendAddJson.setDescription(roadEdit.getText().toString());
                friendAddJson.setReportTime(rqTe.getText().toString());
                friendAddJson.setUnintNo(MyApplication.spUtils.getString("UnitNo"));
                friendAddJson.setUserID(MyApplication.spUtils.getString("UserID"));
                friendAddJson.setLon(Longitude+"");
                friendAddJson.setLat(Latitude+"");
                friendAddJson.setLocation(isBridgeFw.getText().toString());
                friendAddJson.setConType(TYPE);
                String tijiaodates = JSON.toJSONString(friendAddJson);
                Log.i("图片提交的二", "=====" + tijiaodates);
                mPresenter.upData(tijiaodates,CircleoffriendsActivity.this);
                break;
        }
    }
    private void getTYPE() {
        if (!Utils.isNull(roadEdit.getText().toString())) {
            TYPE = "1";
        } else {
            if (circleoffriendsGridAdapter.getListImgUrl().size() > 0) {
                for (int i = 0; i < circleoffriendsGridAdapter.getListImgUrl().size(); i++) {
                    if (!Utils.isNull(circleoffriendsGridAdapter.getListImgUrl().get(i).getVideo()) && !Utils.isNull(circleoffriendsGridAdapter.getListImgUrl().get(i).getPic())) {
                        TYPE = "4";
                        break;
                    } else {
                        TYPE = "2";
                    }
                }
            } else {
                TYPE = "1";
            }
        }

    }

    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    public void getData(String s) {
        if (circleoffriendsGridAdapter.getListImgUrl().size() > 0) {
            ArrayList<VideoBean> listImgUrl = circleoffriendsGridAdapter.getListImgUrl();
            for (int i = 0; i < listImgUrl.size(); i++) {
                listImgUrl.get(i).setGuid(ID);
                listImgUrl.get(i).setReportTime(rqTe.getText().toString());
                listImgUrl.get(i).setBridgeCode(getIntent().getStringExtra("CODE"));
                Log.e("onClick:测试图片 ", listImgUrl.get(i).getPic());
                if (listImgUrl.get(i).getVideo() != null) {
                    Log.e("onClick:测试图片 ", listImgUrl.get(i).getVideo());
                }
            }
            gesdt(listImgUrl);
        } else {
            refreshDataMethod();
        }
    }
    private void gesdt(ArrayList<VideoBean> listImgUrl) {
        AddImg uploadWaihandleDataDialog = new AddImg(this, listImgUrl);
        uploadWaihandleDataDialog.show();
    }
    public void refreshDataMethod() {
        Intent intent = new Intent();
        setResult(GETFRIEND, intent);
        finish();
    }
}


package com.threelinksandonedefense.myapplication.completesectionfilling;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.lxj.matisse.CaptureMode;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;

import com.threelinksandonedefense.myapplication.monthlyeport.MinePopupWindow;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.NoScroolGridView;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class CompleteSectionFillingActivity extends MVPBaseActivity<CompleteSectionFillingContract.View, CompleteSectionFillingPresenter> implements CompleteSectionFillingContract.View {
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
    @Bind(R.id.handle_img)
    ImageView handleImg;
    @Bind(R.id.routecoding_te)
    TextView routecodingTe;
    @Bind(R.id.ed_routecoding)
    TextView edRoutecoding;
    @Bind(R.id.rel_routecoding)
    RelativeLayout relRoutecoding;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.ed_qdzh)
    EditText edQdzh;
    @Bind(R.id.rel_qdzh)
    RelativeLayout relQdzh;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.ed_zdzh)
    EditText edZdzh;
    @Bind(R.id.rel_zdzh)
    RelativeLayout relZdzh;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.switch2)
    Switch switch2;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    @Bind(R.id.delete)
    ImageView delete;
    private OptionsPickerView progressCustomOptions;
    private List<String> datas = new ArrayList();
    private int childViewPosition;//点击拍照获取的childveiw下标
    private CompleteGridAdapter completeGridAdapter;
    private Map<Integer, CompleteGridAdapter> mapAdapter = new HashMap<>();
    private MinePopupWindow minePopupWindow;
    private List<CompleteInitDataBean.DATABean.LxinfosBean> DATABean = new ArrayList<>();
    private String Lxbm, Lxmc;

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    private String PicData = "";
    private int REQUEST_CODE_CHOOSE = 66;
    private int REQUEST_CODE = 88;
    private String Pic = "";
    private String Issync = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_completesection);
        ButterKnife.bind(this);
        initdata();
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

    private void initdata() {
        title.setText(Utils.replaceNull(getIntent().getStringExtra("name")));
        mPresenter.initdata(getIntent().getStringExtra("xmid"), CompleteSectionFillingActivity.this);
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.add);
        listPicture.add(addPicture);
        final int childPosition = layRel.getChildCount();
        completeGridAdapter = new CompleteGridAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(completeGridAdapter);
        mapAdapter.put(childPosition, completeGridAdapter);
    }

    @OnClick({R.id.go_back, R.id.add, R.id.ed_routecoding, R.id.handle_img, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.delete:
                Glide.with(this).load(R.drawable.add).into(handleImg);
                delete.setVisibility(View.GONE);
                Pic = "";
                break;
            case R.id.add:
                if (Utils.isNull(edQdzh.getText().toString())) {
                    ToastUtils.showShortToast("请输入起点桩号");
                    return;
                }
                if (Utils.isNull(edZdzh.getText().toString())) {
                    ToastUtils.showShortToast("请输入终点桩号");
                    return;
                }
                if (Double.valueOf(edQdzh.getText().toString())>Double.valueOf(edZdzh.getText().toString())){
                    ToastUtils.showShortToast("您输入的起点桩号大于终点桩号，请重新输入");
                    return;
                }
                CompleteSectionFiliingJson completeSectionFiliingJson = new CompleteSectionFiliingJson();
                completeSectionFiliingJson.setJdybid(getIntent().getStringExtra("jdybid"));
                completeSectionFiliingJson.setXmid(getIntent().getStringExtra("xmid"));
                completeSectionFiliingJson.setLxbm(Lxbm);
                completeSectionFiliingJson.setLxmc(Lxmc);
                completeSectionFiliingJson.setQdzh(Utils.replaceNull(edQdzh.getText().toString()));
                completeSectionFiliingJson.setZdzh(Utils.replaceNull(edZdzh.getText().toString()));
                completeSectionFiliingJson.setTbdwmc(MyApplication.spUtils.getString("UnitNo"));
                completeSectionFiliingJson.setTbr(MyApplication.spUtils.getString("UserID"));
                ArrayList<String> listPic = new ArrayList<>();
                if (completeGridAdapter.getListImgUrl().size() > 0) {
                    for (int i = 0; i < completeGridAdapter.getListImgUrl().size(); i++) {
                        listPic.add(Utils.bmpToBase64String(completeGridAdapter.getListImgUrl().get(i)));
                    }
                }
                completeSectionFiliingJson.setPicList(listPic);
                completeSectionFiliingJson.setPicUrl("");
                completeSectionFiliingJson.setIssync(Issync);
                String json = JSON.toJSONString(completeSectionFiliingJson);
                Log.e( "张成昆", json);
                mPresenter.SaveJdybInfo(json, getIntent().getStringExtra("name"),CompleteSectionFillingActivity.this);
                break;
            case R.id.handle_img:
                if (Pic.equals("")) {
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(CompleteSectionFillingActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(layRel, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.ed_routecoding:
                progressCustomOptions = new OptionsPickerView.Builder(CompleteSectionFillingActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        edRoutecoding.setText(datas.get(options1));
                        Lxbm = DATABean.get(options1).getLxbm();
                        Lxmc = DATABean.get(options1).getLxmc();
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
                break;
        }
    }

    @Override
    public void onRequestError(String msg) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final CompleteGridAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
            final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
            final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
            String imgPath = Matisse.obtainCropResult(data);
            listPicture.remove(listPicture.size() - 1);
            Bitmap bitmap = Utils.getImageThumbnail(imgPath, Utils.dip2px(CompleteSectionFillingActivity.this, 480),
                    Utils.dip2px(CompleteSectionFillingActivity.this, 480));
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
                Glide.with(this).load(Matisse.obtainCropResult(data)).into(handleImg);
                delete.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void getData(CompleteInitDataBean.DATABean initDataBean) {
        DATABean = initDataBean.getLxinfos();
        if (!Utils.isNull(initDataBean.getXmimage())) {
            Glide.with(this).load(initDataBean.getXmimage()).into(handleImg);
            Pic = initDataBean.getXmimage();
            delete.setVisibility(View.VISIBLE);
        }
        itemNumber.setText(Utils.replaceNull(initDataBean.getXmbm()));
        district.setText(Utils.replaceNull(initDataBean.getSzxzqh()));
        company.setText(Utils.replaceNull(initDataBean.getTbdwmc()));
        if (initDataBean.getLxinfos().size() > 0) {
            for (int i = 0; i < initDataBean.getLxinfos().size(); i++) {
                datas.add(initDataBean.getLxinfos().get(i).getLxbm() + " " + initDataBean.getLxinfos().get(i).getLxmc());
            }
            Lxbm = initDataBean.getLxinfos().get(0).getLxbm();
            Lxmc = initDataBean.getLxinfos().get(0).getLxmc();
            edRoutecoding.setText(datas.get(0));
        }

    }

    @Override
    public void getData2() {
        if (!Utils.isNull(PicData)) {
            List<String> picBean = new ArrayList<>();
            picBean.add(Utils.bmpToBase64String(PicData));
            AddPicJson AddPicJson = new AddPicJson();
            AddPicJson.setJdybid(getIntent().getStringExtra("xmid"));
            AddPicJson.setPicList(picBean);
            String json = JSON.toJSONString(AddPicJson);
            mPresenter.addPic(json, CompleteSectionFillingActivity.this);
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
        Matisse.from(CompleteSectionFillingActivity.this)
                .choose(MimeType.ofAll()) //显示所有文件类型，比如图片和视频，
                .isCrop(true)//开启裁剪，默认不开启
                .maxSelectable(1)
                .forResult(REQUEST_CODE); //请求码
    }

    /**
     * 照相获取
     */
    public void getFromCamera() {
        Matisse.from(CompleteSectionFillingActivity.this)
                .jumpCapture(CaptureMode.Image)//只拍照片
                .isCrop(true)
                .forResult(REQUEST_CODE);
    }
}

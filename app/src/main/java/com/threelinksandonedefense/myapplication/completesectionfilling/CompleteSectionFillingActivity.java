package com.threelinksandonedefense.myapplication.completesectionfilling;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.monthlyeport.MonthGridAdapter;
import com.threelinksandonedefense.myapplication.monthlyeport.MonthlyeportActivity;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.utils.NoScroolGridView;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.util.ArrayList;
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
    private OptionsPickerView progressCustomOptions;
    private List<String> datas = new ArrayList();
    private int childViewPosition;//点击拍照获取的childveiw下标
    private CompleteGridAdapter completeGridAdapter;
    private Map<Integer, CompleteGridAdapter> mapAdapter = new HashMap<>();
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private int REQUEST_CODE_CHOOSE = 66;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_completesection);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
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

    @OnClick({R.id.go_back, R.id.add, R.id.ed_routecoding,R.id.handle_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.add:
                break;
            case R.id.handle_img:

                break;
            case R.id.ed_routecoding:
                progressCustomOptions = new OptionsPickerView.Builder(CompleteSectionFillingActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        edRoutecoding.setText(datas.get(options1));
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
        }
    }

    @Override
    public void getData(CompleteInitDataBean.DATABean initDataBean) {
        itemNumber.setText(Utils.replaceNull(initDataBean.getXmbm()));
        district.setText(Utils.replaceNull(initDataBean.getSzxzqh()));
        company.setText(Utils.replaceNull(initDataBean.getTbdwmc()));
        if (initDataBean.getLxinfos().size()>0){
            for (int i = 0; i < initDataBean.getLxinfos().size(); i++) {
                datas.add(initDataBean.getLxinfos().get(i).getLxbm()+" "+initDataBean.getLxinfos().get(i).getLxmc());
            }
            edRoutecoding.setText(datas.get(0));
        }

    }
}

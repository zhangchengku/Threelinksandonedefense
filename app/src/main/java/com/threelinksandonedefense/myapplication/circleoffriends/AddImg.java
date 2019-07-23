package com.threelinksandonedefense.myapplication.circleoffriends;

/**
 * Created by 张成昆 on 2019-7-3.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.BaseBean;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.Urls;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;

/**
 * Created by 张成昆 on 2019-4-3.
 */

public class AddImg {
    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Activity activity;
    private ArrayList<VideoBean> listDataLocal;
    private VideoBean baseInfo;//当前正在上传的条目
    private int pro = 0;
    private final int MSG = 1;
    private final int UPLOAD_SUCCESS = 2;
    private Handler mHandler;
    private int total;//总条数
    private final int SAVE_DISEASE_TAG = 3;//上传病害数据调用网络接口标识
    private int currentUploadItem=0;//当前正在上传的条目下标
    private int currentUploadItemCurrentCount=0;//当前正在上传的条目包含的病害信息的个数正在上传的病害信息的下标
    private ValueAnimator valueAnimator;
    private String loadingStr;
    private String[] dotText = {""," . ", " . . ", " . . ."};
    private String nrstring;
    private File file;
    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public AddImg(Activity context, ArrayList<VideoBean> listDataLocal) {
        this.activity = context;
        this.listDataLocal = listDataLocal;

        dialog = new Dialog(context, R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(context).inflate(
                R.layout.data_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Utils.dip2px(context, 300),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(viewDialog, params);
        progressBar = (ProgressBar) viewDialog
                .findViewById(R.id.load_offline_data_progressbar);
        titleTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_title_txt);
        contentTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_value_txt);
        vAnimTxt=(TextView)viewDialog.findViewById(R.id.load_offline_data_title_anim_txt);

        titleTxt.setText("数据上传中");
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 4).setDuration(1000);
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int i = (int) animation.getAnimatedValue();
                    vAnimTxt.setText(dotText[i % dotText.length]);
                }
            });
        }
        valueAnimator.start();

        total = listDataLocal.size();
        String totalNumber = String.valueOf(total);
        String currentLoadNumber = "1";
        setWarmContent(totalNumber, currentLoadNumber);
        progressBar.setMax(total);
        //创建一个Handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //处理消息
                switch (msg.what) {
                    case MSG:
                        //设置滚动条和text的值
                        progressBar.setProgress(pro);
                        String totalNumber = String.valueOf(total);
                        if(pro<total){
                            String currentLoadNumber = String.valueOf(pro + 1);
                            setWarmContent(totalNumber, currentLoadNumber);
                        }
                        break;
                    case UPLOAD_SUCCESS:
                        if(activity instanceof CircleoffriendsActivity){
                            ((CircleoffriendsActivity) activity).refreshDataMethod();
                        }
                        ToastUtils.showShortToast("上传成功");
                        close();
                        break;
                    default:
                        break;
                }
            }
        };

        start();
    }

    private void saveDiseaseNetwork(VideoBean baseInfo) {
        if (baseInfo != null ) {
            String pathSBlob = Utils.bmpToBase64String(baseInfo.getPic());
            Map<String,String> params = new HashMap<>();
            params.put("guid",baseInfo.getGuid());
            params.put("reportTime",baseInfo.getReportTime());
            params.put("bridgeCode",baseInfo.getBridgeCode());
            params.put("bytes",pathSBlob);
            if (baseInfo.getVideo()!=null){
                file = new File(baseInfo.getVideo());
                OkGo.<String>post(Urls.SERVER + "SaveImgVideo")
                        .params("video",file,baseInfo.getVideo())
                        .params(params)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseBean baseInfo = JSON.parseObject(response.body(), BaseBean.class);
                                if (baseInfo != null) {
                                    if ("1".equals(baseInfo.getSTATE())) {
                                        currentUploadItemCurrentCount++;
                                        successNext();
                                    }
                                }else{
                                    if(dialog!=null&&dialog.isShowing()){
                                        dialog.cancel();
                                    }
                                }
                            }
                        });
            }else {
                OkGo.<String>post(Urls.SERVER + "SaveImgVideo")
                        .params(params)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseBean baseInfo = JSON.parseObject(response.body(), BaseBean.class);
                                if (baseInfo != null) {
                                    if ("1".equals(baseInfo.getSTATE())) {
                                        currentUploadItemCurrentCount++;
                                        successNext();
                                    }
                                }else{
                                    if(dialog!=null&&dialog.isShowing()){
                                        dialog.cancel();
                                    }
                                }
                            }
                        });
            }
        }
    }
    /**
     * 上传一条成功之后继续上传下一条
     */
    public void successNext() {
        pro += 1;
        Message msg = new Message();
        msg.what = MSG;
        mHandler.sendMessage(msg);
        if (pro >= total) {
            //如果是编辑页面的立即上传 上传成功后需要删除数据库里的记录\
            Message msgLoad = new Message();
            msgLoad.what = UPLOAD_SUCCESS;
            mHandler.sendMessageDelayed(msgLoad,500);
        } else {
            currentUploadItem+=1;
            start();
        }
    }
    private void start(){
        baseInfo=listDataLocal.get(currentUploadItem);
        currentUploadItemCurrentCount=0;
        saveDiseaseNetwork(baseInfo);
    }

    /**
     * 设置对话框的提示内容
     *
     * @param totalNumber       总共需要上传的病害的个数
     * @param currentLoadNumber 当前正在上传第几个病害
     */
    public void setWarmContent(String totalNumber, String currentLoadNumber) {
        String content = String.format(activity.getString(R.string.disease_data_uploading_content), totalNumber, currentLoadNumber);
        contentTxt.setText(content);
    }
    /**
     * 显示
     */
    public void show() {
        dialog.show();
    }

    /**
     * 关闭
     */
    public void close() {
        if(dialog!=null&&dialog.isShowing()){
            dialog.cancel();
        }
    }
}



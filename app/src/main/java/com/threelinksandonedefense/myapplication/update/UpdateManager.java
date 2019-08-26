package com.threelinksandonedefense.myapplication.update;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.Urls;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;


/**
 * 软件升级
 *
 * @author shichenwei
 */

public class UpdateManager {
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
//    Map<String, String> versionMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    private Context mContext;
    /* 更新进度条 */
    private HorizontalProgressBarWithNumber mProgress;
    private Dialog mDownloadDialog;
    private int currentVersionCode;
    private int serverVersionCode;
    private int minVersionCode;
    private String currentVersionName;
    private String serverVersionName;
    private String minVersionName;
    private String updateMessage;
    private int updateSign = 0;
    private String source;//login,setting

    private AppVersionBO appVersionBO;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    public UpdateManager(Context context, String source) {
        this.source = source;
        this.mContext = context;
    }

    class UpdateThread extends Thread {
        public void run() {
            isUpdate();
        }
    }
    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        new UpdateThread().start();
    }
    /**
     * 检测是否有版本更新
     */
    private void isUpdate() {
        // 获取当前软件版本
        getVersionCode(mContext);
        OkGo.<String>get(Urls.SERVER + "GDSTYF/UpdateVisionInfo")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AppVersionBO videoVos2 = JSON.parseObject(response.body(), AppVersionBO.class);
                        appVersionBO = videoVos2;
                        serverVersionCode = Integer.valueOf(appVersionBO.getDATA().get(0).getVersionCode());
                        serverVersionName = appVersionBO.getDATA().get(0).getAppVision();
                        updateMessage = appVersionBO.getDATA().get(0).getUpdateMessage();
                        minVersionCode = Integer.valueOf(appVersionBO.getDATA().get(0).getMinVersionCode());
                        minVersionName = appVersionBO.getDATA().get(0).getMinVersionName();
                        // 版本判断

                        if (minVersionCode > currentVersionCode) {//必须更新
                            showMustUpdateDialog();
                            return;
                        }
                        if (serverVersionCode > currentVersionCode) {//可选择更新
                            showUpdateDialog();
                            return;
                        }
                    }
                });
    }
    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private void getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            currentVersionCode = packInfo.versionCode;
            currentVersionName = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示软件更新对话框
     */
    private void showUpdateDialog() {
        // 构造对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("软件更新");
        StringBuilder message = new StringBuilder();
        message.append("当前版本:");
        message.append(currentVersionName);
        message.append(" 最新版本:");
        message.append(serverVersionName);
        message.append("\n新特性:\n");
        message.append(updateMessage);
        builder.setMessage(message.toString());
        builder.setCancelable(false);
        // 更新
        builder.setPositiveButton("更新",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 显示下载对话框
                        showDownloadDialog();
                    }
                });
        // 稍后更新
        builder.setNegativeButton("下次再说",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示软件更新对话框
     */
    private void showMustUpdateDialog() {
        // 构造对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("软件更新");
        StringBuilder message = new StringBuilder();
        message.append("当前版本过低，必须升级才能继续使用！");
        builder.setMessage(message.toString());
        builder.setCancelable(false);
        // 更新
        builder.setPositiveButton("更新",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 显示下载对话框
                        showDownloadDialog();
                    }
                });
    /*	// 稍后更新
        builder.setNegativeButton("退出",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						((Activity)mContext).finish();
					}
				});*/
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 无需更新对话框
     */
    private void showNoUpdateDialog() {
        // 构造对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("版本检测");
        StringBuilder message = new StringBuilder();
        message.append("当前版本:");
        message.append(currentVersionName);
        message.append("，已是最新，无需更新！");
        builder.setMessage(message.toString());
        builder.setCancelable(false);
        // 更新
        builder.setPositiveButton("确定",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
        builder.setTitle("软件更新");
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.lay_update, null);
        mProgress = (HorizontalProgressBarWithNumber) v.findViewById(R.id.update_progress);
        builder.setView(v);
        builder.setCancelable(false);
        // 取消更新
        builder.setNegativeButton("取消",
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 设置取消状态
                        cancelUpdate = true;
                /*if(updateSign==2){
                    ((Activity)mContext).finish();
				}*/
                    }
                });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 下载文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     *
     * @author shichenwei
     * @date 2012-4-26
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
                    mSavePath = sdDir.toString() + "/cinema";
                } else {
                    mSavePath = MyApplication.DOWNLOAD_PATH; //手机内存位置
                }
                URL url = new URL(appVersionBO.getDATA().get(0).getUrl());
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                // 获取文件大小
                int length = conn.getContentLength();
                // 创建输入流
                InputStream is = conn.getInputStream();
                File file = new File(mSavePath);
                // 判断文件目录是否存在
                if (!file.exists()) {
                    file.mkdir();
                }
                File apkFile = new File(mSavePath, MyApplication.appFileName);
                //修改权限，使系统的intent可以去安装
                String[] command = {"chmod", "777", mSavePath + "/" + MyApplication.appFileName};
                String[] command2 = {"chmod", "777", mSavePath};
                ProcessBuilder builder = new ProcessBuilder(command);
                ProcessBuilder builder2 = new ProcessBuilder(command2);
                try {
                    builder.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream fos = new FileOutputStream(apkFile);
                int count = 0;
                // 缓存
                byte buf[] = new byte[1024];
                // 写入到文件中
                do {
                    int numread = is.read(buf);
                    count += numread;
                    // 计算进度条位置
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWNLOAD);
                    if (numread <= 0) {
                        //下载完成后修改已下载文件的读写权限
                        builder2.start();
                        mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                        break;
                    }
                    // 写入文件
                    fos.write(buf, 0, numread);
                } while (!cancelUpdate);// 点击取消就停止下载.
                fos.close();
                is.close();
//				}
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    /**
     * 安装 apk 文件
     */
    private void installApk() {
        File apkfile = new File(mSavePath, MyApplication.appFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.threelinksandonedefense.myapplication.fileprovider", apkfile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (mContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            mContext.startActivity(intent);
        }
    }
}
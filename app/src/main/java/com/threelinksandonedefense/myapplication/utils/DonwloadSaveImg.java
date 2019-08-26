package com.threelinksandonedefense.myapplication.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by 张成昆 on 2019-5-31.
 */

public class DonwloadSaveImg {
    private static Context context;
    private static Bitmap mBitmap;
    private static String mSaveMessage = "失败";
    public static void donwloadImg(Context contexts, Bitmap bitmap) {
        context = contexts;
        mBitmap = bitmap;
        new Thread(saveFileRunnable).start();
    }
    private static Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                saveFile(mBitmap);
//                mSaveMessage = "图片保存成功！";
            } catch (IOException e) {
//                mSaveMessage = "图片保存失败！";
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };
    private static Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
          ToastUtils.showShortToast(mSaveMessage);
        }
    };
    /**
     * 保存图片 DonwloadSaveImg.donwloadImg(MainActivity.this,"http://106.37.229.146:5566/Files/YHXC/TEMP/PIC/1556606716549_41e3a36c-ae74-4d9d-b87a-41e5cda28032.jpg");//iPath
     * @param bm
     * @throws IOException
     */
    public static void saveFile(Bitmap bm ) throws IOException {
        File dirFile = new File(Environment.getExternalStorageDirectory().getPath());
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File myCaptureFile = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/threelinksandonedefense/" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        //把图片保存后声明这个广播事件通知系统相册有新图片到来
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}


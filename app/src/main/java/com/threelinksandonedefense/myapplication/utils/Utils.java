package com.threelinksandonedefense.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Rect;
/**
 * Created by 张成昆 on 2019-6-20.
 */

public class Utils {
    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    public static String rerurn0(String str) {
        if (str == null||str.equals("")) {
            return "0";
        }
        return str;
    }
    public static boolean isNull(String str) {
        if (str != null) {
            str = str.trim();
        }
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
    public static int getWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    public static int dip2px(Context context, double dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * (double)scale + 0.5D);
    }
    public static void hideInputWindow(Activity context) {
        if (context == null) {
            return;
        }
        final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    public static Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth;
        if(h < w) {
            beWidth = width;
            width = height;
            height = beWidth;
        }

        beWidth = w / width;
        int beHeight = h / height;
        int be;
        if(beWidth < beHeight) {
            be = beHeight;
        } else {
            be = beWidth;
        }

        if(be <= 0) {
            be = 1;
        }

        options.inSampleSize = be;

        try {
            InputStream instream = new FileInputStream(imagePath);
            bitmap = BitmapFactory.decodeStream(instream, (Rect)null, options);
        } catch (Exception var11) {
            var11.printStackTrace();
            bitmap = null;
        }

        return bitmap;
    }
    public static String bmpToBase64String(String imgPath) {

        // 用io流读取到要上传的图片，用Base64编码成字节流的字符串,得到uploadBuffer（要传到webservice接口上）
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String uploadBuffer = "";
        try {
            if (Utils.isNull(imgPath)) {
                return "";
            }
            fis = new FileInputStream(imgPath);
            byte[] buffer = new byte[16];
            int count = -1;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            // 进行Base64编码得到字符串
            uploadBuffer = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadBuffer;
    }
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
//            transparencyBar(activity);
//            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(colorId);
        }
    }
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private static  Bitmap bitmap;
    public static Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }
    public static void i(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }
}

package com.threelinksandonedefense.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.threelinksandonedefense.myapplication.utils.SPUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by 张成昆 on 2019-6-20.
 */

public class MyApplication extends Application {
    public static MyApplication app;// 应用实例
    public static SPUtils spUtils;// 本地存储工具类
    public SharedPreferences sp;
    public static final String DOWNLOAD_PATH = "/data/data/threelinksandonedefense/download";
    public static final String appFileName = "threelinksandonedefense.apk";  //版本
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        sp = getSharedPreferences("sp_threelinksandonedefense", Context.MODE_PRIVATE);
        spUtils = new SPUtils();
        initOkGo();
        ZXingLibrary.initDisplayOpinion(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                            //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }
}

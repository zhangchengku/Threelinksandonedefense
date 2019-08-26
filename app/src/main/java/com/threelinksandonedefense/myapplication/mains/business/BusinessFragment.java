package com.threelinksandonedefense.myapplication.mains.business;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.code.MyWebView;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BusinessFragment extends MVPBaseFragment<BusinessContract.View, BusinessPresenter> implements BusinessContract.View {


    @Bind(R.id.pro_schedule)
    ContentLoadingProgressBar mProSchedule;
    @Bind(R.id.webview)
    MyWebView mWebview;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_business, null);
        ButterKnife.bind(this, view);
        initview();
        initlinstener();
        return view;
    }

    private void initlinstener() {
    }

    private void initview() {
        //启用数据库
        mWebview.getSettings().setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = getContext().getApplicationContext().getDir("Code_Activity", Context.MODE_PRIVATE).getPath();
        mWebview.getSettings().setGeolocationDatabasePath(dir);

        //启用地理定位
        mWebview.getSettings().setGeolocationEnabled(true);

        //开启DomStorage缓存
        mWebview.getSettings().setDomStorageEnabled(true);
        //帮助WebView处理各种通知、请求事件
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProSchedule.setVisibility(View.VISIBLE);
                //开始
                /**
                 * 网页重定向时会执行多次
                 */
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //网页加载成功
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //网址处理
                /**
                 * 可对指定网址进行拦截
                 */
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //网页加载失败
                /**
                 * 此回调中可进行自定义错误页面，
                 * 遇到错误时示例代码:view.loadUrl("file://android_asset/error.html");
                 */
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProSchedule.setVisibility(View.GONE);
                //网页加载完成
            }
        });
        //辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //网页加载进度
                if (newProgress < 100) {
                    mProSchedule.setProgress(newProgress);
                }
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);

            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        mWebview.loadUrl("http://106.37.229.146:7109/businessindex.html?gydwid=440&gydwmc=广东省交通运输厅&userid=440&username=超级管理员&sptype=2");
        mWebview.addJavascriptInterface(new JsInterface(), "Android");
    }

    public class JsInterface {
        @JavascriptInterface
        public void showToast(String type) {

        }

        @JavascriptInterface
        public void showToast(String type, String sss) {

        }

        @JavascriptInterface
        public void showToast(String type, String UserId, String CJI) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

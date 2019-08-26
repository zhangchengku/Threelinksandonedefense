package com.threelinksandonedefense.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.lxj.matisse.CaptureMode;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;
import com.threelinksandonedefense.myapplication.circleoffriends.CircleoffriendsActivity;
import com.threelinksandonedefense.myapplication.code.CodeActivity;
import com.threelinksandonedefense.myapplication.code.MyWebView;
import com.threelinksandonedefense.myapplication.completesectionfilling.CompleteSectionFillingActivity;
import com.threelinksandonedefense.myapplication.dialog.CommBtnListener;
import com.threelinksandonedefense.myapplication.dialog.CommNotificationDialog;
import com.threelinksandonedefense.myapplication.land.LandActivity;
import com.threelinksandonedefense.myapplication.map.MapActivity;
import com.threelinksandonedefense.myapplication.monthlyeport.MinePopupWindow;
import com.threelinksandonedefense.myapplication.monthlyeport.MonthlyeportActivity;
import com.threelinksandonedefense.myapplication.updatafriends.UpDataFriendsActivity;
import com.threelinksandonedefense.myapplication.update.UpdateManager;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    public static boolean isForeground = false;
    @Bind(R.id.pro_schedule)
    ContentLoadingProgressBar mProSchedule;
    @Bind(R.id.webview)
    MyWebView mWebview;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    private MinePopupWindow minePopupWindow;
    private int REQUEST_CODE = 22;//二维码扫描
    private int REQUEST_CODE_CHOOSE = 66;//相册相机
    private int REQUEST_MONTH = 11;//相册相机
    private CommNotificationDialog logoutWarmDialog;
    private String userId;
    private String XMID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //启用数据库
        mWebview.getSettings().setDatabaseEnabled(true);

        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("Code_Activity", Context.MODE_PRIVATE).getPath();
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
        mWebview.loadUrl("http://106.37.229.146:7109/index.html?gydwid=" + MyApplication.spUtils.getString("UnitNo")
                + "&gydwmc=" + MyApplication.spUtils.getString("UnitName")
                + "&userid=" + MyApplication.spUtils.getString("UserID")
                + "&username=" + MyApplication.spUtils.getString("UserName")
                + "&sptype=" + MyApplication.spUtils.getString("SpType")
        );
        mWebview.addJavascriptInterface(new JsInterface(), "Android");
        new UpdateManager(MainActivity.this, "main").checkUpdate();   //检查更新
    }

    public class JsInterface {
        @JavascriptInterface
        public void showToast(String type) {
        }

        @JavascriptInterface//跳转完成路段
        public void goComplete(String xmid, String name, String jdybid) {
            Intent intent = new Intent(MainActivity.this, CompleteSectionFillingActivity.class);
            intent.putExtra("xmid", xmid);
            intent.putExtra("name", name);
            intent.putExtra("jdybid", jdybid);
            startActivityForResult(intent, REQUEST_MONTH);
        }

        @JavascriptInterface//跳转进度月报
        public void goMonth(String xmid, String name) {
            Intent intent = new Intent(MainActivity.this, MonthlyeportActivity.class);
            intent.putExtra("xmid", xmid);
            intent.putExtra("name", name);
            startActivityForResult(intent, REQUEST_MONTH);
        }

        @JavascriptInterface//上传头像照片
        public void setHead(String userid) {
            userId = userid;
            if (minePopupWindow == null) {
                minePopupWindow = new MinePopupWindow(MainActivity.this, itemOnClick);
            }
            minePopupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM, 0, 0);
        }

        @JavascriptInterface//扫描二维码
        public void goCode() {
            Intent intent = new Intent(MainActivity.this, CodeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

        @JavascriptInterface//退出登陆
        public void goLogout() {
            if (logoutWarmDialog == null) {
                String title = "是否退出登录";
                String okStr = "确定";
                String cancelStr = "取消";
                logoutWarmDialog = new CommNotificationDialog(MainActivity.this, title, okStr, cancelStr, new CommBtnListener() {
                    @Override
                    public void CommOkBtnClick() {
                        MyApplication.spUtils.clear();
                        startActivity(new Intent(MainActivity.this, LandActivity.class));
                        finish();
                    }

                    @Override
                    public void CommCancelBtnClick() {
                    }
                });
            }
            logoutWarmDialog.show();
        }

        @JavascriptInterface//跳转到朋友圈
        public void getFriend(String code, String name) {
            Intent intent = new Intent(MainActivity.this, CircleoffriendsActivity.class);
            intent.putExtra("code", code);
            intent.putExtra("name", name);
            startActivityForResult(intent, REQUEST_MONTH);
        }

        @JavascriptInterface//去修改朋友圈
        public void UpDataFriend(String code) {
            Intent intent = new Intent(MainActivity.this, UpDataFriendsActivity.class);
            intent.putExtra("code", code);
            startActivityForResult(intent, REQUEST_MONTH);
        }

        @JavascriptInterface//弹出键盘
        public void getKeyBoard() {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }

        @JavascriptInterface//上传图片
        public void setPic(String ID) {
            XMID = ID;
            if (minePopupWindow == null) {
                minePopupWindow = new MinePopupWindow(MainActivity.this, itemOnClick);
            }
            minePopupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM, 0, 0);
        }

        @JavascriptInterface//跳转地图海量点
        public void goMap(String Code, String Name) {
            OkGo.<String>get(Urls.SERVER+"GDSTYF/QueryAreaCode")
                    .params("code",Code)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            CodeBean poiBean = JSON.parseObject(response.body(),CodeBean.class);
                            String strhours =poiBean.getDATA().get(0).getPost();
                            String strm = strhours.substring(0,strhours.length()-2);   //截掉
                            Intent intent = new Intent(MainActivity.this, MapActivity.class);
                            intent.putExtra("Code", strm+"00");
                            intent.putExtra("Name", strm+"00");
                            startActivity(intent);
                        }
                    });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        Matisse.from(MainActivity.this)
                .choose(MimeType.ofAll()) //显示所有文件类型，比如图片和视频，
                .isCrop(true)//开启裁剪，默认不开启
                .maxSelectable(1)
                .forResult(REQUEST_CODE_CHOOSE); //请求码
    }

    /**
     * 照相获取
     */
    public void getFromCamera() {
        Matisse.from(MainActivity.this)
                .jumpCapture(CaptureMode.Image)//只拍照片
                .isCrop(true)
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            String imgPath = Matisse.obtainCropResult(data);
            String strBlob = Utils.bmpToBase64String(imgPath);
            if (Utils.isNull(XMID) && !Utils.isNull(userId)) {
                OkGo.<String>post(Urls.SERVER + "GDSTYF/UpdateUserPhoto")
                        .params("userId", userId)
                        .params("bytes", strBlob)
                        .execute(new StringDialogCallback(MainActivity.this) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseBean landBean = JSON.parseObject(response.body(), BaseBean.class);
                                if (landBean.getSTATE().equals("1")) {
                                    mWebview.loadUrl("javascript:callH5()");
                                } else {
                                    ToastUtils.showShortToast(landBean.getMSG());
                                }
                            }
                        });
            } else if (Utils.isNull(userId) && !Utils.isNull(XMID)) {
                List<String> piclist = new ArrayList<>();
                piclist.add(strBlob);
                PICjson PICjson = new PICjson();
                PICjson.setXmid(XMID);
                PICjson.setPicList(piclist);
                String json = JSON.toJSONString(PICjson);
                OkGo.<String>post(Urls.SERVER + "GDSTYF/SaveXmImageInfo")
                        .params("json", json)
                        .execute(new StringDialogCallback(MainActivity.this) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseBean landBean = JSON.parseObject(response.body(), BaseBean.class);
                                if (landBean.getSTATE().equals("1")) {
                                    mWebview.loadUrl("javascript:callH5()");
                                } else {
                                    ToastUtils.showShortToast(landBean.getMSG());
                                }
                            }
                        });
            }
        } else if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    mWebview.loadUrl("http://106.37.229.146:7109/XMLB/dynamic.html?xmid=" + result.split("\\|")[0] + "&xmmc=" + result.split("\\|")[1]);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShortToast("解析二维码失败");
                }
            }
        } else if (requestCode == REQUEST_MONTH) {
            if (resultCode == 666) {
                mWebview.loadUrl("javascript:callH5()");
            }
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }
}
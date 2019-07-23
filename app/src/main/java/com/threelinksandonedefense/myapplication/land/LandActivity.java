package com.threelinksandonedefense.myapplication.land;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxj.xpermission.PermissionConstants;
import com.lxj.xpermission.XPermission;
import com.threelinksandonedefense.myapplication.MainActivity;
import com.threelinksandonedefense.myapplication.MyApplication;
import com.threelinksandonedefense.myapplication.R;
import com.threelinksandonedefense.myapplication.mvp.MVPBaseActivity;
import com.threelinksandonedefense.myapplication.update.UpdateManager;
import com.threelinksandonedefense.myapplication.utils.ToastUtils;
import com.threelinksandonedefense.myapplication.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
public class LandActivity extends MVPBaseActivity<LandContract.View, LandPresenter> implements LandContract.View {
    @Bind(R.id.land_username)
    EditText landUsername;
    @Bind(R.id.land_password)
    EditText landPassword;
    @Bind(R.id.land_bu)
    TextView landBu;
    @Bind(R.id.land_lin)
    LinearLayout landLin;
    private String username = "";
    private String password = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_land);
        ButterKnife.bind(this);
        initview();
        listener();
    }
    private void listener() {
        landBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XPermission xPermission;
                xPermission = XPermission.create(LandActivity.this, PermissionConstants.STORAGE//存储控件
                        , PermissionConstants.LOCATION//位置信息
                        , PermissionConstants.CAMERA//相机
                        , PermissionConstants.MICROPHONE//录制音频
                );
                xPermission.callback(new XPermission.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        landBu.setClickable(false);
                        username = landUsername.getText().toString().trim();
                        password = landPassword.getText().toString().trim();
                        if (Utils.isNull(username) && Utils.isNull(password)) {
                            ToastUtils.showShortToast("请输入正确的账号或密码");
                            return;
                        } else {
                            mPresenter.Land(username, password, LandActivity.this);
                        }
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShortToast("没有权限，无法使用该功能");
                    }
                }).request();

            }
        });
    }
    private void initview() {
        if (!Utils.isNull(MyApplication.spUtils.getString("UserName"))) {
            landUsername.setText(MyApplication.spUtils.getString("UserName"));
        }
        if (!Utils.isNull(MyApplication.spUtils.getString("PassWord"))) {
            landPassword.setText(MyApplication.spUtils.getString("PassWord"));
        }
    }

    @Override
    public void onRequestError(String msg) {
        landBu.setClickable(true);
        ToastUtils.showShortToast(msg);
    }

    @Override
    public void getLand(List<LandBean.DATABean> s) {
        LandBean.DATABean loginInfos = s.get(0);
        MyApplication.spUtils.put("UserName", username);
        MyApplication.spUtils.put("PassWord", password);
        MyApplication.spUtils.put("UserID", loginInfos.getUserID());
        MyApplication.spUtils.put("UserName", loginInfos.getUserName());
        MyApplication.spUtils.put("UnitNo", loginInfos.getUnitNo());
        MyApplication.spUtils.put("UnitName", loginInfos.getUnitName());
        MyApplication.spUtils.put("ShortUnitName", loginInfos.getShortUnitName());
        MyApplication.spUtils.put("ParentID", loginInfos.getParentID());
        MyApplication.spUtils.put("UserType", loginInfos.getUserType());
        MyApplication.spUtils.put("Post", loginInfos.getPost());
        MyApplication.spUtils.put("SpType", loginInfos.getSpType());
        Intent intent = new Intent(LandActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

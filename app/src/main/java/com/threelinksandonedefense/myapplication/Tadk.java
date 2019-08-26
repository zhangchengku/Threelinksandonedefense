package com.threelinksandonedefense.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.threelinksandonedefense.myapplication.callback.StringDialogCallback;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 张成昆 on 2019-6-24.
 */

public class Tadk extends AppCompatActivity {


    @Bind(R.id.bu)
    Button bu;
    @Bind(R.id.spinner1)
    Spinner spinner1;
    @Bind(R.id.lay_rel)
    RelativeLayout layRel;
    private String Code = "44";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_task);
        ButterKnife.bind(this);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<String>get(Urls.SERVER+"GDSTYF/QueryAreaCode")
                        .params("code","440520220")
                        .execute(new StringDialogCallback(Tadk.this) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                CodeBean poiBean = JSON.parseObject(response.body(),CodeBean.class);
                                String strhours =poiBean.getDATA().get(0).getPost();
                                String strm = strhours.substring(0,strhours.length()-2);   //截掉
                                Log.e( "张成昆: ",strm+"00");
                            }
                        });
            }
        });
    }
}
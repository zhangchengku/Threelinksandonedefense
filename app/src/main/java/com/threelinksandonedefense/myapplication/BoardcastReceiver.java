package com.threelinksandonedefense.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 张成昆 on 2019-7-18.
 */

public class BoardcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent pushintent=new Intent(context,PushService.class);//启动极光推送的服务
        context.startService(pushintent);
    }
}


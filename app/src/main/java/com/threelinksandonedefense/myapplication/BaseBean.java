package com.threelinksandonedefense.myapplication;

/**
 * Created by 张成昆 on 2019-6-25.
 */

public class BaseBean<T> {
    public String STATE;
    public String MSG;
    public T DATA;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public T getDATA() {
        return DATA;
    }

    public void setDATA(T DATA) {
        this.DATA = DATA;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }
}

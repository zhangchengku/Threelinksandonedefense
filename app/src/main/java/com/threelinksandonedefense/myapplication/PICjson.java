package com.threelinksandonedefense.myapplication;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-8.
 */

public class PICjson {

    /**
     * Xmid : 01c352fa2bd4423c94eb01b7ad713f2e
     * PicList : ["/9j/4AAQSkZJRgABXChgKRBaQm12Fjabks6dnTRjklzXNranHn4YKvyZu/EHw7A1oTs0TfRTjEdiSDg8aJxxVgaLUXneJ309tluqtOZYa9hkqH+LCpm5HD79Liv/2Q=="]
     */

    private String Xmid;
    private List<String> PicList;

    public String getXmid() {
        return Xmid;
    }

    public void setXmid(String Xmid) {
        this.Xmid = Xmid;
    }

    public List<String> getPicList() {
        return PicList;
    }

    public void setPicList(List<String> PicList) {
        this.PicList = PicList;
    }
}

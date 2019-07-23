package com.threelinksandonedefense.myapplication.circleoffriends;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-3.
 */

public class FriendAddJson {
    private String ID; //ID             ID
    private String xmId;//项目id         xmId
    private String Description;//描述
    private String ReportTime;//时间
    private String UnintNo;
    private String UserID;
    private String Lon;
    private String Lat;
    private String Location;//位置信息
    private String ConType;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getXmId() {
        return xmId;
    }

    public void setXmId(String xmId) {
        this.xmId = xmId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String reportTime) {
        ReportTime = reportTime;
    }

    public String getUnintNo() {
        return UnintNo;
    }

    public void setUnintNo(String unintNo) {
        UnintNo = unintNo;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getConType() {
        return ConType;
    }

    public void setConType(String conType) {
        ConType = conType;
    }
}

package com.threelinksandonedefense.myapplication.updatafriends;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-4.
 */

public class UpDataInitBean {

    /**
     * STATE : 1
     * DATA : [{"ID":"52ce53c9-96e1-4497-891e-b4b28f5fa3c1","Description":"ghh","ReportTime":"2019-07-04 09:00","UnintNo":"440520220","UnitName":"揭西县交通运输局","xmId":"FE00025E36154F55937115B8B1A98309","xmmc":"上砂镇水打坝水库路","Lon":"116.172824","Lat":"40.059398","Location":"北京市海淀区紫雀路9号院靠近同有科技大厦","COMPLETION":999,"COMPLETIONNAME":null,"files":"a9c49725-6878-4889-a89c-eb985ab16c93|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/845355fb-b5fd-4c12-865a-724c19939fe4.jpg,ce18b6ae-50c1-4a19-8278-8b05992ba9d0|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/7f778e8c-2e0d-4d07-9d82-0313d0d89f64.jpg|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/181a5d51-3d8d-4c0a-a523-889b6444f599.mp4"}]
     */

    private String STATE;
    private List<DATABean> DATA;
    private String MSG;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * ID : 52ce53c9-96e1-4497-891e-b4b28f5fa3c1
         * Description : ghh
         * ReportTime : 2019-07-04 09:00
         * UnintNo : 440520220
         * UnitName : 揭西县交通运输局
         * xmId : FE00025E36154F55937115B8B1A98309
         * xmmc : 上砂镇水打坝水库路
         * Lon : 116.172824
         * Lat : 40.059398
         * Location : 北京市海淀区紫雀路9号院靠近同有科技大厦
         * COMPLETION : 999
         * COMPLETIONNAME : null
         * files : a9c49725-6878-4889-a89c-eb985ab16c93|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/845355fb-b5fd-4c12-865a-724c19939fe4.jpg,ce18b6ae-50c1-4a19-8278-8b05992ba9d0|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/7f778e8c-2e0d-4d07-9d82-0313d0d89f64.jpg|http://106.37.229.146:7106/Files/XMCJ/2019-07-04/181a5d51-3d8d-4c0a-a523-889b6444f599.mp4
         */

        private String ID;
        private String Description;
        private String ReportTime;
        private String UnintNo;
        private String UnitName;
        private String xmId;
        private String xmmc;
        private String Lon;
        private String Lat;
        private String Location;
        private String COMPLETION;
        private Object COMPLETIONNAME;
        private String files;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String ReportTime) {
            this.ReportTime = ReportTime;
        }

        public String getUnintNo() {
            return UnintNo;
        }

        public void setUnintNo(String UnintNo) {
            this.UnintNo = UnintNo;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String UnitName) {
            this.UnitName = UnitName;
        }

        public String getXmId() {
            return xmId;
        }

        public void setXmId(String xmId) {
            this.xmId = xmId;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getLon() {
            return Lon;
        }

        public void setLon(String Lon) {
            this.Lon = Lon;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String Lat) {
            this.Lat = Lat;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getCOMPLETION() {
            return COMPLETION;
        }

        public void setCOMPLETION(String COMPLETION) {
            this.COMPLETION = COMPLETION;
        }

        public Object getCOMPLETIONNAME() {
            return COMPLETIONNAME;
        }

        public void setCOMPLETIONNAME(Object COMPLETIONNAME) {
            this.COMPLETIONNAME = COMPLETIONNAME;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }
    }
}

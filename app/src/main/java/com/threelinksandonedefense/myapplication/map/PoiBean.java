package com.threelinksandonedefense.myapplication.map;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-17.
 */

public class PoiBean {
    private String STATE;
    private List<DATABean> DATA;
    private String ZS;
    private String LC;

    public String getZS() {
        return ZS;
    }

    public void setZS(String ZS) {
        this.ZS = ZS;
    }

    public String getLC() {
        return LC;
    }

    public void setLC(String LC) {
        this.LC = LC;
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
         * Guid_obj : 0008b91a-282b-48b4-b67f-b8a1e3c41d14
         * kjfl : 小桥
         * lon : 101.079613
         * lat : 31.924657
         */
        private String Guid_obj;
        private String kjfl;
        private double lon;
        private double lat;

        public String getGuid_obj() {
            return Guid_obj;
        }

        public void setGuid_obj(String guid_obj) {
            Guid_obj = guid_obj;
        }

        public String getKjfl() {
            return kjfl;
        }

        public void setKjfl(String kjfl) {
            this.kjfl = kjfl;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
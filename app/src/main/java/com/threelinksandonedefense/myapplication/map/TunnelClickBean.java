package com.threelinksandonedefense.myapplication.map;

import java.util.List;

public class TunnelClickBean {

    /**
     * STATE : 1
     * DATA : [{"Guid_obj":54298,"sdbm":"X518440606U0010","sdmc":"陈村下穿隧道","lxbm":"X518","lxmc":"陈藤线","zh":2.02,"jsdj":"一级","kjfl":"中隧道","lon":113.235878,"lat":22.956764,"sc":600,"areacode":"440606","parentcode":"440600","gydwname":"佛山市顺德区公路局"}]
     */

    private String STATE;
    private List<DATABean> DATA;

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
         * Guid_obj : 54298
         * sdbm : X518440606U0010
         * sdmc : 陈村下穿隧道
         * lxbm : X518
         * lxmc : 陈藤线
         * zh : 2.02
         * jsdj : 一级
         * kjfl : 中隧道
         * lon : 113.235878
         * lat : 22.956764
         * sc : 600.0
         * areacode : 440606
         * parentcode : 440600
         * gydwname : 佛山市顺德区公路局
         */

        private int Guid_obj;
        private String sdbm;
        private String sdmc;
        private String lxbm;
        private String lxmc;
        private double zh;
        private String jsdj;
        private String kjfl;
        private double lon;
        private double lat;
        private double sc;
        private String areacode;
        private String parentcode;
        private String gydwname;

        public int getGuid_obj() {
            return Guid_obj;
        }

        public void setGuid_obj(int Guid_obj) {
            this.Guid_obj = Guid_obj;
        }

        public String getSdbm() {
            return sdbm;
        }

        public void setSdbm(String sdbm) {
            this.sdbm = sdbm;
        }

        public String getSdmc() {
            return sdmc;
        }

        public void setSdmc(String sdmc) {
            this.sdmc = sdmc;
        }

        public String getLxbm() {
            return lxbm;
        }

        public void setLxbm(String lxbm) {
            this.lxbm = lxbm;
        }

        public String getLxmc() {
            return lxmc;
        }

        public void setLxmc(String lxmc) {
            this.lxmc = lxmc;
        }

        public double getZh() {
            return zh;
        }

        public void setZh(double zh) {
            this.zh = zh;
        }

        public String getJsdj() {
            return jsdj;
        }

        public void setJsdj(String jsdj) {
            this.jsdj = jsdj;
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

        public double getSc() {
            return sc;
        }

        public void setSc(double sc) {
            this.sc = sc;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getParentcode() {
            return parentcode;
        }

        public void setParentcode(String parentcode) {
            this.parentcode = parentcode;
        }

        public String getGydwname() {
            return gydwname;
        }

        public void setGydwname(String gydwname) {
            this.gydwname = gydwname;
        }
    }
}

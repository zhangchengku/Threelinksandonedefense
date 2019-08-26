package com.threelinksandonedefense.myapplication.map;

import java.util.List;

public class PointClickBean {

    /**
     * STATE : 1
     * DATA : [{"Guid_obj":25897,"qlbm":"C001440904L0010","qlmc":"坭涌桥","lxbm":"C001","lxmc":"企石垌--坭涌","zh":0.118,"jsdj":"三类","kjfl":"中桥","lon":111.356876,"lat":21.742732,"qc":48,"areacode":"440904","parentcode":"440900","gydwname":"电白区地方公路管理站"}]
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
         * Guid_obj : 25897
         * qlbm : C001440904L0010
         * qlmc : 坭涌桥
         * lxbm : C001
         * lxmc : 企石垌--坭涌
         * zh : 0.118
         * jsdj : 三类
         * kjfl : 中桥
         * lon : 111.356876
         * lat : 21.742732
         * qc : 48.0
         * areacode : 440904
         * parentcode : 440900
         * gydwname : 电白区地方公路管理站
         */

        private int Guid_obj;
        private String qlbm;
        private String qlmc;
        private String lxbm;
        private String lxmc;
        private double zh;
        private String jsdj;
        private String kjfl;
        private double lon;
        private double lat;
        private double qc;
        private String areacode;
        private String parentcode;
        private String gydwname;

        public int getGuid_obj() {
            return Guid_obj;
        }

        public void setGuid_obj(int Guid_obj) {
            this.Guid_obj = Guid_obj;
        }

        public String getQlbm() {
            return qlbm;
        }

        public void setQlbm(String qlbm) {
            this.qlbm = qlbm;
        }

        public String getQlmc() {
            return qlmc;
        }

        public void setQlmc(String qlmc) {
            this.qlmc = qlmc;
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

        public double getQc() {
            return qc;
        }

        public void setQc(double qc) {
            this.qc = qc;
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

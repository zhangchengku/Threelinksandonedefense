package com.threelinksandonedefense.myapplication.map;

import java.util.List;

public class Sclerosis20Click {


    /**
     * STATE : 1
     * DATA : [{"xmmc":"省道-青田洞","xmbm":"2019441223053","xmjd":"已完工","xmtype":"通达改造工程","tbdwmc":"广宁县交通运输局","tbsj":"2019-06","ljwctz":15.85}]
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
         * xmmc : 省道-青田洞
         * xmbm : 2019441223053
         * xmjd : 已完工
         * xmtype : 通达改造工程
         * tbdwmc : 广宁县交通运输局
         * tbsj : 2019-06
         * ljwctz : 15.85
         */

        private String xmmc;
        private String xmbm;
        private String xmjd;
        private String xmtype;
        private String tbdwmc;
        private String tbsj;
        private double ljwctz;

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getXmbm() {
            return xmbm;
        }

        public void setXmbm(String xmbm) {
            this.xmbm = xmbm;
        }

        public String getXmjd() {
            return xmjd;
        }

        public void setXmjd(String xmjd) {
            this.xmjd = xmjd;
        }

        public String getXmtype() {
            return xmtype;
        }

        public void setXmtype(String xmtype) {
            this.xmtype = xmtype;
        }

        public String getTbdwmc() {
            return tbdwmc;
        }

        public void setTbdwmc(String tbdwmc) {
            this.tbdwmc = tbdwmc;
        }

        public String getTbsj() {
            return tbsj;
        }

        public void setTbsj(String tbsj) {
            this.tbsj = tbsj;
        }

        public double getLjwctz() {
            return ljwctz;
        }

        public void setLjwctz(double ljwctz) {
            this.ljwctz = ljwctz;
        }
    }
}

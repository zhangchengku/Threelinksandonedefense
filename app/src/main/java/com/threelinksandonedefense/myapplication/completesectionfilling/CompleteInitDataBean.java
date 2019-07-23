package com.threelinksandonedefense.myapplication.completesectionfilling;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-25.
 */

public class CompleteInitDataBean {

    /**
     * STATE : 1
     * DATA : {"xmid":"8FDB4AD42139411D877209F4AB252D63","xmbm":"2019441803038","szxzqh":"清远市清新区","tbdwmc":"清新区交通运输局","xmimage":"","lxinfos":[[{"lxbm":"U997441803","lxmc":"114辅道-海边村"},{"lxbm":"Y377441803","lxmc":"姨坑口-大姨坑"}]]}
     */

    private String STATE;
    private List<DATABean> DATA;
    private String MSG;

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

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



    public static class DATABean {
        /**
         * xmid : 8FDB4AD42139411D877209F4AB252D63
         * xmbm : 2019441803038
         * szxzqh : 清远市清新区
         * tbdwmc : 清新区交通运输局
         * xmimage :
         * lxinfos : [[{"lxbm":"U997441803","lxmc":"114辅道-海边村"},{"lxbm":"Y377441803","lxmc":"姨坑口-大姨坑"}]]
         */

        private String xmid;
        private String xmbm;
        private String szxzqh;
        private String tbdwmc;
        private String xmimage;
        private List<LxinfosBean> lxinfos;

        public String getXmid() {
            return xmid;
        }

        public void setXmid(String xmid) {
            this.xmid = xmid;
        }

        public String getXmbm() {
            return xmbm;
        }

        public void setXmbm(String xmbm) {
            this.xmbm = xmbm;
        }

        public String getSzxzqh() {
            return szxzqh;
        }

        public void setSzxzqh(String szxzqh) {
            this.szxzqh = szxzqh;
        }

        public String getTbdwmc() {
            return tbdwmc;
        }

        public void setTbdwmc(String tbdwmc) {
            this.tbdwmc = tbdwmc;
        }

        public String getXmimage() {
            return xmimage;
        }

        public void setXmimage(String xmimage) {
            this.xmimage = xmimage;
        }

        public List<LxinfosBean> getLxinfos() {
            return lxinfos;
        }

        public void setLxinfos(List<LxinfosBean> lxinfos) {
            this.lxinfos = lxinfos;
        }
        public static class LxinfosBean {
            /**
             * lxbm : U997441803
             * lxmc : 114辅道-海边村
             */

            private String lxbm;
            private String lxmc;

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
        }
    }
}

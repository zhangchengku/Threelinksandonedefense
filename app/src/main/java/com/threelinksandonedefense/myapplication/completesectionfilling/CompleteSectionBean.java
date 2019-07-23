package com.threelinksandonedefense.myapplication.completesectionfilling;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-22.
 */

public class CompleteSectionBean {

    /**
     * STATE : 1
     * DATA : [{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"龙门县交通运输局","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"},{"xmid":"00051912C8F643EEB45EDECBBA5DAC0F","xmbm":"","szxzqh":"惠州市龙门县","tbdwmc":"采集账户","xmimage":null,"lxinfos":"C097441324*上围\u2014龙洞"}]
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
         * xmid : 00051912C8F643EEB45EDECBBA5DAC0F
         * xmbm :
         * szxzqh : 惠州市龙门县
         * tbdwmc : 采集账户
         * xmimage : null
         * lxinfos : C097441324*上围—龙洞
         */

        private String xmid;
        private String xmbm;
        private String szxzqh;
        private String tbdwmc;
        private Object xmimage;
        private String lxinfos;

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

        public Object getXmimage() {
            return xmimage;
        }

        public void setXmimage(Object xmimage) {
            this.xmimage = xmimage;
        }

        public String getLxinfos() {
            return lxinfos;
        }

        public void setLxinfos(String lxinfos) {
            this.lxinfos = lxinfos;
        }
    }
}

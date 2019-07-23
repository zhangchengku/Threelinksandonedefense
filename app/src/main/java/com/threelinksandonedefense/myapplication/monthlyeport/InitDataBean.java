package com.threelinksandonedefense.myapplication.monthlyeport;

import java.util.List;

/**
 * Created by 张成昆 on 2019-6-22.
 */

public class InitDataBean {
    /**
     * DATA : [{"xmid":"182A8452D19C46E4AA9166590C65D50B","xmbm":"2019445222018","xmlx":"200人以上自然村路面硬化","szxzqh":"揭阳市揭西县","tbdwmc":"揭西县交通运输局","xmimage":"http://106.37.229.146:7106/Files/XMJDYB/2019-07-09/23e0da4b-de63-403f-94af-a79321556413.jpg","yjwcsj":"2019-05-01","xmjd":"未开工","sfys":"是","zlpd":"良","sfmsxm":"是","wcqlgs":12,"wwcqlgs":12,"wcpfm":0,"wwcpfm":0,"dywctz":36,"ljdwzj":36,"ljdwzyzj":36,"ljdwstz":0,"ljdwsjpt":0,"ljdwxjpt":0,"ljdwczjpt":0,"ljwctz":12,"ljwczytz":69,"ljwcstz":0,"ljwcsjpt":0,"ljwcxjpt":0,"ljwcczjpt":0,"gcjzsm":"轻轻巧巧","dywcxx":"乒乒乓乓","issync":1,"jdimages":"http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/7ef40f43-e649-4287-bc30-8c71275d5455.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/76002ff4-0351-4b6a-8b1f-ea8c65e3e7a4.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/529728c6-931a-4c1b-92ea-4f35ab0f5264.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/8db09c0c-eeb8-4725-aa04-996d059b8f50.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/373580c8-f8b9-40fe-b713-cd58899f84eb.jpg,http://106.37.229.146:7106/Files/JCSJ/TEMP/PIC/c7825f30-1601-471a-a81f-d28f77b41d3f.jpg,http://106.37.229.146:7106/Files/XMIMG/FE00025E36154F55937115B8B1A98309/2019-07-05/11a33629-318e-45c9-882d-1ac58ec63856.jpg,http://106.37.229.146:7106/Files/XMIMG/F879F63ACE374F559728450DC7842708/2019-07-05/6076e760-e644-4b40-936d-6a473d5742ad.jpg,http://106.37.229.146:7106/Files/XMIMG/182A8452D19C46E4AA9166590C65D50B/2019-07-09/aab9ecfc-a585-4c85-a417-9c4ae9ae1524.jpg,http://106.37.229.146:7106/Files/XMIMG/182A8452D19C46E4AA9166590C65D50B/2019-07-11/e061d375-5b74-4995-884f-6d4ed01e60cc.jpg,http://106.37.229.146:7106/Files/XMIMG/FE00025E36154F55937115B8B1A98309/2019-07-11/3bb593d2-7266-4d3d-a636","xmtbtype":"2"}]
     * STATE : 1
     * COMDATA : [{"ZdName":"未开工","ZdValue":"1"},{"ZdName":"在建","ZdValue":"2"},{"ZdName":"已完工","ZdValue":"3"},{"ZdName":"交竣工","ZdValue":"4"}]
     */

    private String STATE;
    private List<DATABean> DATA;
    private List<COMDATABean> COMDATA;
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

    public List<COMDATABean> getCOMDATA() {
        return COMDATA;
    }

    public void setCOMDATA(List<COMDATABean> COMDATA) {
        this.COMDATA = COMDATA;
    }

    public static class DATABean {
        /**
         * xmid : 182A8452D19C46E4AA9166590C65D50B
         * xmbm : 2019445222018
         * xmlx : 200人以上自然村路面硬化
         * szxzqh : 揭阳市揭西县
         * tbdwmc : 揭西县交通运输局
         * xmimage : http://106.37.229.146:7106/Files/XMJDYB/2019-07-09/23e0da4b-de63-403f-94af-a79321556413.jpg
         * yjwcsj : 2019-05-01
         * xmjd : 未开工
         * sfys : 是
         * zlpd : 良
         * sfmsxm : 是
         * wcqlgs : 12
         * wwcqlgs : 12
         * wcpfm : 0
         * wwcpfm : 0
         * dywctz : 36
         * ljdwzj : 36
         * ljdwzyzj : 36
         * ljdwstz : 0
         * ljdwsjpt : 0
         * ljdwxjpt : 0
         * ljdwczjpt : 0
         * ljwctz : 12
         * ljwczytz : 69
         * ljwcstz : 0
         * ljwcsjpt : 0
         * ljwcxjpt : 0
         * ljwcczjpt : 0
         * gcjzsm : 轻轻巧巧
         * dywcxx : 乒乒乓乓
         * issync : 1
         * jdimages : http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/7ef40f43-e649-4287-bc30-8c71275d5455.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/76002ff4-0351-4b6a-8b1f-ea8c65e3e7a4.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/529728c6-931a-4c1b-92ea-4f35ab0f5264.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/8db09c0c-eeb8-4725-aa04-996d059b8f50.jpg,http://106.37.229.146:7106/Files/XMIMG/00051912C8F643EEB45EDECBBA5DAC0F/2019-06-22/373580c8-f8b9-40fe-b713-cd58899f84eb.jpg,http://106.37.229.146:7106/Files/JCSJ/TEMP/PIC/c7825f30-1601-471a-a81f-d28f77b41d3f.jpg,http://106.37.229.146:7106/Files/XMIMG/FE00025E36154F55937115B8B1A98309/2019-07-05/11a33629-318e-45c9-882d-1ac58ec63856.jpg,http://106.37.229.146:7106/Files/XMIMG/F879F63ACE374F559728450DC7842708/2019-07-05/6076e760-e644-4b40-936d-6a473d5742ad.jpg,http://106.37.229.146:7106/Files/XMIMG/182A8452D19C46E4AA9166590C65D50B/2019-07-09/aab9ecfc-a585-4c85-a417-9c4ae9ae1524.jpg,http://106.37.229.146:7106/Files/XMIMG/182A8452D19C46E4AA9166590C65D50B/2019-07-11/e061d375-5b74-4995-884f-6d4ed01e60cc.jpg,http://106.37.229.146:7106/Files/XMIMG/FE00025E36154F55937115B8B1A98309/2019-07-11/3bb593d2-7266-4d3d-a636
         * xmtbtype : 2
         */

        private String xmid;
        private String xmbm;
        private String xmlx;
        private String szxzqh;
        private String tbdwmc;
        private String xmimage;
        private String yjwcsj;
        private String xmjd;
        private String sfys;
        private String zlpd;
        private String sfmsxm;
        private String wcqlgs;
        private String wwcqlgs;
        private String wcpfm;
        private String wwcpfm;
        private String dywctz;
        private String ljdwzj;
        private String ljdwzyzj;
        private String ljdwstz;
        private String ljdwsjpt;
        private String ljdwxjpt;
        private String ljdwczjpt;
        private String ljwctz;
        private String ljwczytz;
        private String ljwcstz;
        private String ljwcsjpt;
        private String ljwcxjpt;
        private String ljwcczjpt;
        private String gcjzsm;
        private String dywcxx;
        private String issync;
        private String jdimages;
        private String xmtbtype;

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

        public String getXmlx() {
            return xmlx;
        }

        public void setXmlx(String xmlx) {
            this.xmlx = xmlx;
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

        public String getYjwcsj() {
            return yjwcsj;
        }

        public void setYjwcsj(String yjwcsj) {
            this.yjwcsj = yjwcsj;
        }

        public String getXmjd() {
            return xmjd;
        }

        public void setXmjd(String xmjd) {
            this.xmjd = xmjd;
        }

        public String getSfys() {
            return sfys;
        }

        public void setSfys(String sfys) {
            this.sfys = sfys;
        }

        public String getZlpd() {
            return zlpd;
        }

        public void setZlpd(String zlpd) {
            this.zlpd = zlpd;
        }

        public String getSfmsxm() {
            return sfmsxm;
        }

        public void setSfmsxm(String sfmsxm) {
            this.sfmsxm = sfmsxm;
        }

        public String getWcqlgs() {
            return wcqlgs;
        }

        public void setWcqlgs(String wcqlgs) {
            this.wcqlgs = wcqlgs;
        }

        public String getWwcqlgs() {
            return wwcqlgs;
        }

        public void setWwcqlgs(String wwcqlgs) {
            this.wwcqlgs = wwcqlgs;
        }

        public String getWcpfm() {
            return wcpfm;
        }

        public void setWcpfm(String wcpfm) {
            this.wcpfm = wcpfm;
        }

        public String getWwcpfm() {
            return wwcpfm;
        }

        public void setWwcpfm(String wwcpfm) {
            this.wwcpfm = wwcpfm;
        }

        public String getDywctz() {
            return dywctz;
        }

        public void setDywctz(String dywctz) {
            this.dywctz = dywctz;
        }

        public String getLjdwzj() {
            return ljdwzj;
        }

        public void setLjdwzj(String ljdwzj) {
            this.ljdwzj = ljdwzj;
        }

        public String getLjdwzyzj() {
            return ljdwzyzj;
        }

        public void setLjdwzyzj(String ljdwzyzj) {
            this.ljdwzyzj = ljdwzyzj;
        }

        public String getLjdwstz() {
            return ljdwstz;
        }

        public void setLjdwstz(String ljdwstz) {
            this.ljdwstz = ljdwstz;
        }

        public String getLjdwsjpt() {
            return ljdwsjpt;
        }

        public void setLjdwsjpt(String ljdwsjpt) {
            this.ljdwsjpt = ljdwsjpt;
        }

        public String getLjdwxjpt() {
            return ljdwxjpt;
        }

        public void setLjdwxjpt(String ljdwxjpt) {
            this.ljdwxjpt = ljdwxjpt;
        }

        public String getLjdwczjpt() {
            return ljdwczjpt;
        }

        public void setLjdwczjpt(String ljdwczjpt) {
            this.ljdwczjpt = ljdwczjpt;
        }

        public String getLjwctz() {
            return ljwctz;
        }

        public void setLjwctz(String ljwctz) {
            this.ljwctz = ljwctz;
        }

        public String getLjwczytz() {
            return ljwczytz;
        }

        public void setLjwczytz(String ljwczytz) {
            this.ljwczytz = ljwczytz;
        }

        public String getLjwcstz() {
            return ljwcstz;
        }

        public void setLjwcstz(String ljwcstz) {
            this.ljwcstz = ljwcstz;
        }

        public String getLjwcsjpt() {
            return ljwcsjpt;
        }

        public void setLjwcsjpt(String ljwcsjpt) {
            this.ljwcsjpt = ljwcsjpt;
        }

        public String getLjwcxjpt() {
            return ljwcxjpt;
        }

        public void setLjwcxjpt(String ljwcxjpt) {
            this.ljwcxjpt = ljwcxjpt;
        }

        public String getLjwcczjpt() {
            return ljwcczjpt;
        }

        public void setLjwcczjpt(String ljwcczjpt) {
            this.ljwcczjpt = ljwcczjpt;
        }

        public String getGcjzsm() {
            return gcjzsm;
        }

        public void setGcjzsm(String gcjzsm) {
            this.gcjzsm = gcjzsm;
        }

        public String getDywcxx() {
            return dywcxx;
        }

        public void setDywcxx(String dywcxx) {
            this.dywcxx = dywcxx;
        }

        public String getIssync() {
            return issync;
        }

        public void setIssync(String issync) {
            this.issync = issync;
        }

        public String getJdimages() {
            return jdimages;
        }

        public void setJdimages(String jdimages) {
            this.jdimages = jdimages;
        }

        public String getXmtbtype() {
            return xmtbtype;
        }

        public void setXmtbtype(String xmtbtype) {
            this.xmtbtype = xmtbtype;
        }
    }

    public static class COMDATABean {
        /**
         * ZdName : 未开工
         * ZdValue : 1
         */

        private String ZdName;
        private String ZdValue;

        public String getZdName() {
            return ZdName;
        }

        public void setZdName(String ZdName) {
            this.ZdName = ZdName;
        }

        public String getZdValue() {
            return ZdValue;
        }

        public void setZdValue(String ZdValue) {
            this.ZdValue = ZdValue;
        }
    }





}

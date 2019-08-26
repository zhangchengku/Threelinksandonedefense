package com.threelinksandonedefense.myapplication.map;

import java.util.List;

public class Sclerosis20Bean {
    private String STATE;
    private List<DATABean> DATA;
    private String QNJH;
    private String WKG;
    private String ZJ;
    private String YGW;

    public String getYGW() {
        return YGW;
    }

    public void setYGW(String YGW) {
        this.YGW = YGW;
    }

    public String getQNJH() {
        return QNJH;
    }

    public void setQNJH(String QNJH) {
        this.QNJH = QNJH;
    }

    public String getWKG() {
        return WKG;
    }

    public void setWKG(String WKG) {
        this.WKG = WKG;
    }

    public String getZJ() {
        return ZJ;
    }

    public void setZJ(String ZJ) {
        this.ZJ = ZJ;
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
        private String id;
        private String xmlx;
        private double lon;
        private double lat;
        private String xmjd;

        public String getXmjd() {
            return xmjd;
        }

        public void setXmjd(String xmjd) {
            this.xmjd = xmjd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXmlx() {
            return xmlx;
        }

        public void setXmlx(String xmlx) {
            this.xmlx = xmlx;
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

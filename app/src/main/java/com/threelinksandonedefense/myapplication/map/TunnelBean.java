package com.threelinksandonedefense.myapplication.map;

import java.util.List;

public class TunnelBean {

    /**
     * STATE : 1
     * DATA : [{"guid_obj":54292,"kjfl":"短隧道","lon":111.497019,"lat":22.967911},{"guid_obj":54293,"kjfl":"短隧道","lon":112.137587,"lat":23.037798},{"guid_obj":54294,"kjfl":"短隧道","lon":116.547498,"lat":23.659417},{"guid_obj":54295,"kjfl":"短隧道","lon":113.21498,"lat":24.781483},{"guid_obj":54296,"kjfl":"短隧道","lon":113.212782,"lat":24.786063},{"guid_obj":54297,"kjfl":"短隧道","lon":111.663473,"lat":23.003505},{"guid_obj":54298,"kjfl":"中隧道","lon":113.235878,"lat":22.956764},{"guid_obj":54299,"kjfl":"短隧道","lon":113.291057,"lat":22.830504},{"guid_obj":54300,"kjfl":"中隧道","lon":113.293202,"lat":22.817878},{"guid_obj":54301,"kjfl":"短隧道","lon":113.31612,"lat":22.007987},{"guid_obj":54302,"kjfl":"短隧道","lon":113.318454,"lat":22.00827},{"guid_obj":54303,"kjfl":"短隧道","lon":116.660134,"lat":23.671062},{"guid_obj":54304,"kjfl":"中隧道","lon":113.402374,"lat":22.492669},{"guid_obj":54305,"kjfl":"短隧道","lon":113.401775,"lat":24.272888},{"guid_obj":54306,"kjfl":"短隧道","lon":114.189285,"lat":22.706829},{"guid_obj":54307,"kjfl":"短隧道","lon":114.187159,"lat":22.706116},{"guid_obj":54308,"kjfl":"短隧道","lon":113.300226,"lat":25.137617},{"guid_obj":54309,"kjfl":"短隧道","lon":113.26213,"lat":25.298795},{"guid_obj":54310,"kjfl":"短隧道","lon":112.403252,"lat":24.88648},{"guid_obj":54311,"kjfl":"长隧道","lon":113.096328,"lat":23.038094},{"guid_obj":54312,"kjfl":"中隧道","lon":113.096525,"lat":23.015595},{"guid_obj":54313,"kjfl":"中隧道","lon":113.091039,"lat":22.972764},{"guid_obj":54314,"kjfl":"长隧道","lon":113.085652,"lat":22.92948},{"guid_obj":54315,"kjfl":"长隧道","lon":113.157783,"lat":23.0695}]
     * ZS : 24
     * LC : 11113.64
     */

    private String STATE;
    private int ZS;
    private double LC;
    private List<DATABean> DATA;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public int getZS() {
        return ZS;
    }

    public void setZS(int ZS) {
        this.ZS = ZS;
    }

    public double getLC() {
        return LC;
    }

    public void setLC(double LC) {
        this.LC = LC;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * guid_obj : 54292
         * kjfl : 短隧道
         * lon : 111.497019
         * lat : 22.967911
         */

        private int guid_obj;
        private String kjfl;
        private double lon;
        private double lat;

        public int getGuid_obj() {
            return guid_obj;
        }

        public void setGuid_obj(int guid_obj) {
            this.guid_obj = guid_obj;
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

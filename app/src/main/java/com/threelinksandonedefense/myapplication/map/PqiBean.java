package com.threelinksandonedefense.myapplication.map;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-7-18.
 */

public class PqiBean {
    public String status;// 状态信息 VARCHAR 正确：Y；错误：N
    public String error_code;// 错误代码
    public String error_msg;// 错误信息
    private PCIInfo results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public PCIInfo getResults() {
        return results;
    }

    public void setResults(PCIInfo results) {
        this.results = results;
    }

    public static class PCIInfo {
        private ArrayList<RoadsInfo> road_indicator_list;

        public ArrayList<RoadsInfo> getRoad_indicator_list() {
            return road_indicator_list;
        }

        public void setRoad_indicator_list(ArrayList<RoadsInfo> road_indicator_list) {
            this.road_indicator_list = road_indicator_list;
        }
    }
    public static class RoadsInfo {
        private String road_code;// 公路编码
        private String road_level;// 线路行政等级
        private String road_name;// 公路名称
        private ArrayList<RoadPCIInfo> road_indicator_distribute;// 公路PCI分布结果

        public String getRoad_code() {
            return road_code;
        }

        public void setRoad_code(String road_code) {
            this.road_code = road_code;
        }

        public String getRoad_level() {
            return road_level;
        }

        public void setRoad_level(String road_level) {
            this.road_level = road_level;
        }

        public String getRoad_name() {
            return road_name;
        }

        public void setRoad_name(String road_name) {
            this.road_name = road_name;
        }

        public ArrayList<RoadPCIInfo> getRoad_indicator_distribute() {
            return road_indicator_distribute;
        }

        public void setRoad_indicator_distribute(ArrayList<RoadPCIInfo> road_indicator_distribute) {
            this.road_indicator_distribute = road_indicator_distribute;
        }
    }
    public static class RoadPCIInfo {
        private ArrayList<LonLatsInfo> road_lon_lat_list;// 经纬度集合
        private IndicatorInfo indicator_info;// 指标集合

        public IndicatorInfo getIndicator_info() {
            return indicator_info;
        }

        public void setIndicator_info(IndicatorInfo indicator_info) {
            this.indicator_info = indicator_info;
        }

        public ArrayList<LonLatsInfo> getRoad_lon_lat_list() {
            return road_lon_lat_list;
        }

        public void setRoad_lon_lat_list(ArrayList<LonLatsInfo> road_lon_lat_list) {
            this.road_lon_lat_list = road_lon_lat_list;
        }
    }
    public static class LonLatsInfo {
        private String longitude;// 经度
        private String latitude;// 纬度
        private String end_longitude;// 末端经度
        private String end_latitude;// 末端纬度

        public String getEnd_longitude() {
            return end_longitude;
        }

        public void setEnd_longitude(String end_longitude) {
            this.end_longitude = end_longitude;
        }

        public String getEnd_latitude() {
            return end_latitude;
        }

        public void setEnd_latitude(String end_latitude) {
            this.end_latitude = end_latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
    public static class IndicatorInfo {
        private String indicator_id;// 指标ID
        private String indicator_value;// 指标名称
        private String indicator_num;// 指标值
        private String indicator_grade;// 指标等级

        public String getIndicator_id() {
            return indicator_id;
        }

        public void setIndicator_id(String indicator_id) {
            this.indicator_id = indicator_id;
        }

        public String getIndicator_value() {
            return indicator_value;
        }

        public void setIndicator_value(String indicator_value) {
            this.indicator_value = indicator_value;
        }

        public String getIndicator_num() {
            return indicator_num;
        }

        public void setIndicator_num(String indicator_num) {
            this.indicator_num = indicator_num;
        }

        public String getIndicator_grade() {
            return indicator_grade;
        }

        public void setIndicator_grade(String indicator_grade) {
            this.indicator_grade = indicator_grade;
        }
    }
}

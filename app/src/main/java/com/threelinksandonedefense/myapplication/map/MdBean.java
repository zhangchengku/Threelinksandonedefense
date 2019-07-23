package com.threelinksandonedefense.myapplication.map;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-7-16.
 */

public class MdBean {
    public String status;// 状态信息 VARCHAR 正确：Y；错误：N
    public String error_code;// 错误代码
    public String error_msg;// 错误信息
    private RoadInfo results;

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

    public RoadInfo getResults() {
        return results;
    }

    public void setResults(RoadInfo results) {
        this.results = results;
    }
    public static class RoadInfo {
        private String road_level;// 线路行政等级，来自键值
        private String road_name;// 线路名称
        private String road_code;// 线路编码
        private String road_mil_num;// 里程数
        private String road_through_place;// 途径
        private String road_start_station;// 起始桩号
        private String road_end_station;// 终点桩号
        private String road_traffic_num;// 交通量
        private LonLatsInfo road_lon_lat;// 起始桩号信息
        private String has_pci_data;// 是否有PCI分布数据（即路况）
        private String has_img_data;// 是否有大图数据（即前方图像）
        private String picture_path;
        private String province_code;
        private String province_name;
        private ArrayList<RoadLonLatProvince> road_lon_lat_province;// 经纬度集合

        public ArrayList<RoadLonLatProvince> getRoad_lon_lat_province() {
            return road_lon_lat_province;
        }

        public void setRoad_lon_lat_province(ArrayList<RoadLonLatProvince> road_lon_lat_province) {
            this.road_lon_lat_province = road_lon_lat_province;
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

        public String getRoad_code() {
            return road_code;
        }

        public void setRoad_code(String road_code) {
            this.road_code = road_code;
        }

        public String getRoad_mil_num() {
            return road_mil_num;
        }

        public void setRoad_mil_num(String road_mil_num) {
            this.road_mil_num = road_mil_num;
        }

        public String getRoad_through_place() {
            return road_through_place;
        }

        public void setRoad_through_place(String road_through_place) {
            this.road_through_place = road_through_place;
        }

        public String getRoad_start_station() {
            return road_start_station;
        }

        public void setRoad_start_station(String road_start_station) {
            this.road_start_station = road_start_station;
        }

        public String getRoad_end_station() {
            return road_end_station;
        }

        public void setRoad_end_station(String road_end_station) {
            this.road_end_station = road_end_station;
        }

        public String getRoad_traffic_num() {
            return road_traffic_num;
        }

        public void setRoad_traffic_num(String road_traffic_num) {
            this.road_traffic_num = road_traffic_num;
        }

        public LonLatsInfo getRoad_lon_lat() {
            return road_lon_lat;
        }

        public void setRoad_lon_lat(LonLatsInfo road_lon_lat) {
            this.road_lon_lat = road_lon_lat;
        }

        public String getHas_pci_data() {
            return has_pci_data;
        }

        public void setHas_pci_data(String has_pci_data) {
            this.has_pci_data = has_pci_data;
        }

        public String getHas_img_data() {
            return has_img_data;
        }

        public void setHas_img_data(String has_img_data) {
            this.has_img_data = has_img_data;
        }

        public String getPicture_path() {
            return picture_path;
        }

        public void setPicture_path(String picture_path) {
            this.picture_path = picture_path;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }
    }
    public static class LonLatsInfo {
        private String road_station;// 桩号
        private String longitude;// 经度
        private String latitude;// 纬度
        private String end_longitude;// 末端经度
        private String end_latitude;// 末端纬度
        private String current_position;// 当前位置
        private String current_feed_unit;// 管养单位
        private String current_indicator_value;// 当前桩号的指标值

        private String road_way_width;// 路面全宽
        private String road_face_type;// 路面类型
        private String daily_traffic_volume;// 日交通量
        private String road_build_years;// 修建年度
        private String road_conservation_years;// 养护年度
        private String road_surface_condition;// 路面状况
        private String road_conservation_advise;// 养护建议

        private String road_data_year;// 路线数据年份
        private String road_direction;// 路线上下行
        private String Postal_Code;// 邮政编码

        public String getRoad_station() {
            return road_station;
        }

        public void setRoad_station(String road_station) {
            this.road_station = road_station;
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

        public String getCurrent_position() {
            return current_position;
        }

        public void setCurrent_position(String current_position) {
            this.current_position = current_position;
        }

        public String getCurrent_feed_unit() {
            return current_feed_unit;
        }

        public void setCurrent_feed_unit(String current_feed_unit) {
            this.current_feed_unit = current_feed_unit;
        }

        public String getCurrent_indicator_value() {
            return current_indicator_value;
        }

        public void setCurrent_indicator_value(String current_indicator_value) {
            this.current_indicator_value = current_indicator_value;
        }

        public String getRoad_way_width() {
            return road_way_width;
        }

        public void setRoad_way_width(String road_way_width) {
            this.road_way_width = road_way_width;
        }

        public String getRoad_face_type() {
            return road_face_type;
        }

        public void setRoad_face_type(String road_face_type) {
            this.road_face_type = road_face_type;
        }

        public String getDaily_traffic_volume() {
            return daily_traffic_volume;
        }

        public void setDaily_traffic_volume(String daily_traffic_volume) {
            this.daily_traffic_volume = daily_traffic_volume;
        }

        public String getRoad_build_years() {
            return road_build_years;
        }

        public void setRoad_build_years(String road_build_years) {
            this.road_build_years = road_build_years;
        }

        public String getRoad_conservation_years() {
            return road_conservation_years;
        }

        public void setRoad_conservation_years(String road_conservation_years) {
            this.road_conservation_years = road_conservation_years;
        }

        public String getRoad_surface_condition() {
            return road_surface_condition;
        }

        public void setRoad_surface_condition(String road_surface_condition) {
            this.road_surface_condition = road_surface_condition;
        }

        public String getRoad_conservation_advise() {
            return road_conservation_advise;
        }

        public void setRoad_conservation_advise(String road_conservation_advise) {
            this.road_conservation_advise = road_conservation_advise;
        }

        public String getRoad_data_year() {
            return road_data_year;
        }

        public void setRoad_data_year(String road_data_year) {
            this.road_data_year = road_data_year;
        }

        public String getRoad_direction() {
            return road_direction;
        }

        public void setRoad_direction(String road_direction) {
            this.road_direction = road_direction;
        }

        public String getPostal_Code() {
            return Postal_Code;
        }

        public void setPostal_Code(String postal_Code) {
            Postal_Code = postal_Code;
        }
    }
    public static class RoadLonLatProvince {
        private ArrayList<LonLatsInfo> road_lon_lat_list;// 经纬度列表

        public ArrayList<LonLatsInfo> getRoad_lon_lat_list() {
            return road_lon_lat_list;
        }

        public void setRoad_lon_lat_list(ArrayList<LonLatsInfo> road_lon_lat_list) {
            this.road_lon_lat_list = road_lon_lat_list;
        }
    }
}


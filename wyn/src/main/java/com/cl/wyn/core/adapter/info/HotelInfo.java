package com.cl.wyn.core.adapter.info;

import com.cl.wyn.core.util.common.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 *  酒店信息
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelInfo implements  Serializable {

    /**
     * 酒店编号(新)
     */
    @JsonProperty("HotelNo")
    private String hotelNo;
    /**
     * 酒店名称
     */
    @JsonProperty("HotelName")
    private String hotelName;
    /**
     * 排名
     */
    @JsonProperty("Rank")
    private Integer rank;
    /**
     * 酒店简称
     */
    @JsonProperty("HotelShortName")
    private String hotelShortName;

    /**
     * 星级（1-5）
     */
    @JsonProperty("StarLevel")
    private Integer starLevel;
    /**
     * 所在城市编码
     */
    @JsonProperty("City")
    private String city;
    /**
     * 所在城市名称
     */
    @JsonProperty("CityName")
    private String cityName;
    /**
     * 区县编码
     */
    @JsonProperty("Area")
    private String area;
    /**
     * 区县名称
     */
    @JsonProperty("AreaName")
    private String areaName;
    /**
     * 商圈编码
     */
    @JsonProperty("District")
    private String district;
    /**
     * 商圈名称
     */
    @JsonProperty("DistrictName")
    private String districtName;
    /**
     * 电话
     */
    @JsonProperty("Tel")
    private String tel;
    /**
     * 传真
     */
    @JsonProperty("Fax")
    private String fax;
    /**
     * 酒店地址
     */
    @JsonProperty("Address")
    private String address;
    /**
     * 经度
     */
    @JsonProperty("Longitude")
    private String longitude;
    /**
     * 纬度
     */
    @JsonProperty("Latitude")
    private String latitude;
    /**
     * 房量
     */
    @JsonProperty("RoomCount")
    private Integer roomCount;
    /**
     * 最低价格
     */
    @JsonProperty("LowestPrice")
    private BigDecimal lowestPrice;
    /**
     * 入住率
     */
    @JsonProperty("OccupancyRate")
    private String occupancyRate;
    /**
     * 评论数据
     */
    @JsonProperty("CommentCount")
    private String commentCount;
    /**
     * 评分
     */
    @JsonProperty("Score")
    private BigDecimal score;
    /**
     * 酒店缩列图
     */
    @JsonProperty("ImageUrl")
    private String imageUrl;
    /**
     * 酒店简介
     */
    @JsonProperty("Summary")
    private String summary;
    /**
     * 距离
     */
    @JsonProperty("Distance")
    private String distance;
    /**
     * 交通距离与设施服务
     */
    @JsonProperty("FacilitySevice")
    private FacilityServiceInfo facilityServiceInfo;
    /**
     * 品牌1:3好店2:维也纳3:国际店4:维纳斯
     */
    @JsonProperty("Brand")
    private String brand;
    /**
     * 酒店加盟类型1:直营2:加盟
     */
    @JsonProperty("HotelType")
    private String hotelType;
    /**
     * 开业时间
     */
    @JsonProperty("OpeningTime")
    private String openingTime;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FacilityServiceInfo implements Serializable {

        /**
         * wifi
         */
        @JsonProperty("wifi")
        private Integer wifi;
        /**
         * 特色餐饮
         */
        @JsonProperty("specialRestaurant")
        private Integer specialRestaurant;
        /**
         * 停车场
         */
        @JsonProperty("parking")
        private Integer parking;
        /**
         * 音乐体验
         */
        @JsonProperty("musicExperience")
        private Integer musicExperience;
        /**
         * 接送服务
         */
        @JsonProperty("airportShuttle")
        private Integer airportShuttle;
        /**
         * 宴会会议
         */
        @JsonProperty("meetingRoom")
        private Integer meetingRoom;
        /**
         * 商务中心
         */
        @JsonProperty("businessCenter")
        private Integer businessCenter;
        /**
         * 早餐
         */
        @JsonProperty("breakfast")
        private Integer breakfast;
        /**
         * KTV
         */
        @JsonProperty("ktv")
        private Integer ktv;
        /**
         * 游泳池
         */
        @JsonProperty("swimmingPool")
        private Integer swimmingPool;
        /**
         * 健身房
         */
        @JsonProperty("gymnasium")
        private Integer gymnasium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelInfo hotelInfo = (HotelInfo) o;
        if(Objects.equals(hotelName, hotelInfo.hotelName) || Objects.equals(address, hotelInfo.address)
           ||(Objects.equals(longitude, hotelInfo.longitude) && Objects.equals(latitude, hotelInfo.latitude))) {
            return true;
        }
        int priority = 0;
        if(this.getHotelName() != null && hotelInfo.getHotelName() != null) {
            if(dealHotelName(this.getHotelName()).equals(dealHotelName(hotelInfo.getHotelName()))){
                priority++;
            }
        }
        if(this.getTel() != null && hotelInfo.getTel() != null) {
            String tel1 = dealTel(this.getTel());
            String tel2 = dealTel(hotelInfo.getTel());
            if(tel1.contains(tel2) || tel2.contains(tel1) ){
                priority++;
            }
        }
        if(Objects.equals(this.city, hotelInfo.city)){
            priority++;
        }
        if(this.address != null && hotelInfo.getAddress() != null){
            if(this.address.contains(hotelInfo.getAddress()) || hotelInfo.getAddress().endsWith(this.address) ){
                priority++;
            }
        }

        if (StringUtil.notEmpty(this.getLatitude()) && StringUtil.notEmpty(this.getLongitude())
                && StringUtil.notEmpty(hotelInfo.getLatitude()) && StringUtil.notEmpty(hotelInfo.getLongitude())) {
            BigDecimal latitude1 = new BigDecimal(this.getLatitude().trim()).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal latitude2 = new BigDecimal(hotelInfo.getLatitude().trim()).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal longitude1 = new BigDecimal(this.getLongitude().trim()).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal longitude2 = new BigDecimal(hotelInfo.getLongitude().trim()).setScale(3, BigDecimal.ROUND_HALF_UP);
            if (latitude1.equals(latitude2) && longitude1.equals(longitude2)){
                priority++;
            }
        }
        if(priority > 3){
            return true;
        }
        return false;
    }

    public String dealHotelName(String hotelName) {
        return StringUtil.removeTags(hotelName, new String[]{"省", "市", "县", "酒店", "宾馆","中心","饭店"});
    }
    public String dealTel(String tel) {
        return StringUtil.removeTags(tel, new String[]{"-"});
    }

}

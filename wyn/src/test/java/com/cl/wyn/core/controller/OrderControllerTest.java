package com.cl.wyn.core.controller;

import com.cl.wyn.core.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * by cl at 2020/7/2 0002
 */
@Slf4j
public class OrderControllerTest {
    String url = "http://localhost:8081/";

    @Test
    public void confirmBooking() {
        Map<String, String> map = new HashMap<>();
        map.put("hotelId", "732176146942132225");
        map.put("roomId","732176148238172165");
        map.put("checkInTime", "2020-09-03");
        map.put("checkOutTime", "2020-09-04");
        map.put("roomNum", "2");
        map.put("guestCount","1");
        map.put("totalPrice", "332");
        Map<String, String> headMap = new HashMap<>();
        String post = HttpUtil.postJSON(url + "order/confirmBooking", headMap, map);
        log.info(post);

    }

    @Test
    public void booking() {
        Map<String, String> map = new HashMap<>();
        map.put("hotelId", "732176146942132225");
        map.put("roomId","732176148238172165");
        map.put("checkInTime", "2020-08-03");
        map.put("checkOutTime", "2020-08-04");
        map.put("roomNum", "2");
        map.put("totalPrice", "332");

        map.put("occupancy", "1");
        map.put("guestName", "李世民");
        map.put("mobile", "15896583301");
//        map.put("remark", "111");
        map.put("orderNo", "7311498858389831841");
        String post = HttpUtil.postJSON(url + "order/booking", new HashMap<>(), map);
        log.info(post);

    }
    @Test
    public void getOrderInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", "731149885838983184");
        String post = HttpUtil.postJSON(url + "order/getOrderInfo", new HashMap<>(), map);
        log.info(post);
    }
    @Test
    public void cancelOrder(){
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", "731149885838983184");
        String post = HttpUtil.postJSON(url + "order/cancelOrder", new HashMap<>(), map);
        log.info(post);
    }
}

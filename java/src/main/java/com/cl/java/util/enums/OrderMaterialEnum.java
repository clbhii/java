package com.cl.java.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单材料枚举
 * @author cl
 * @since 2018年8月1日
 */
public interface OrderMaterialEnum {
	

	
	/**
	 * 文件类型
	 * @author cl
	 * @since 2018年8月1日
	 */
	@AllArgsConstructor
	enum FileTypeEnum implements BaseEnum<Integer> {
		ID(100, "身份证照片"),
		CAR(200, "车辆照片"),
		REGISTER(300, "面签/登记证照片"),
		GPS(400, "gps"),
		DRIVING(500, "行驶证/驾驶证"),
		REPORT(600, "检测报告"),
		
		OTHER(900, "其他")
		;
		@Getter
		private Integer value;
		@Getter
		private String desc;
	}
	
	/**
	 * 文件子类型
	 * @author cl
	 * @since 2018年8月1日
	 */
	@AllArgsConstructor
	enum FileSubTypeEnum implements BaseEnum<Integer> {
		ID__FRONT(101, "身份证正面照片"),
		ID__BACK(102, "身份证反面照片"),
		ID__HAND(103, "客户手持身份证"),
		
		CAR__FRONT_LEFT(201, "车辆左前方"),
		CAR__LEFT(202, "车辆左侧"),
		CAR__FRONT_SEAT(203, "车辆前排座椅"),
		CAR__INSTRUMENT_PANEL(204, "车辆仪表盘"),
		CAR__BACK_SEAT(205, "车辆后排座椅"),	
		CAR__CENTER_CONTROL(206, "车辆中控盘"),
		CAR__TAIL(207, "车辆尾"),
		CAR__TRUNK(208, "后备箱底板"),
		CAR__BACK_RIGTH(209, "车辆右后方"),
		CAR__VIN(210, "车架号"),
		CAR__BRAND(211, "出厂铭牌"),
		CAR__ENGINE_NO(212, "发动机号"),
		CAR__COMPULSORY_TRAFFIC_INSURANCE(213, "交强险"),
		CAR__BUY_CAR_BILL(214, "购车发票"),
		CAR__FRONT_TYRE(215, "前轮轮胎"),
		CAR__TOP_WINDOW(216, "车顶棚天窗"),
		CAR__WORK_BENCH(217, "工作台"),
		CAR__BAFFLE(218, "挡板饰板"),
		CAR__TAX_BOOK(219, "税本"),
		CAR__WINDSHIELD_VIN(220, "风挡车架号"),
		
		CAR__ENGINE_PART(230, "车辆发动机舱"),
		
		REGISTER__INTERVIEW(301, "面签照"),
		REGISTER__PLEDGE(302, "抵押登记"),
		REGISTER__SPARE_KEY(303, "备用钥匙"),
		REGISTER__CAR(320, "车辆登记"),
		
		GPS__GPS(401, "gps"),
		
		DRIVING__DRIVING_LICENSE(501, "驾驶证"),
		DRIVING__VEHICLE_LICENSE(520, "行驶证"),
		
		REPORT__CHECK_REPORT(601, "检测报告"),
		
		OTHER__SPARE_KEY_RETURN(901, "备用钥匙归还"),
		;
		@Getter
		private Integer value;
		@Getter
		private String desc;
	}
}

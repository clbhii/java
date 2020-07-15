package com.cl.java.jdk8;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class DateTest {
	@Test
	public void test1() {
		LocalDate today = LocalDate.now();
		System.out.println("Today's Local date:" + today);
	}
	@Test
	public void test2() {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		System.out.printf("Year:%d Month:%d day:%d \t %n", year, month, day);
	}
	@Test
	public void test3() {
		LocalDate dateOfBirth = LocalDate.of(2014, 1, 14);
		System.out.println("Your Date of birth is:" + dateOfBirth);
		
	}
	@Test
	public void test4(){
		LocalDate today = LocalDate.now();
		LocalDate date1 = LocalDate.of(2016, 1, 25);
		if(date1.equals(today)){
			System.out.printf("Today %s and date1 %s are same date %n", today, date1);
		}
	}
	@Test
	public void test5() {
		LocalDate today = LocalDate.now();
		LocalDate dateOfBirth = LocalDate.of(2010, 1, 25);
		MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(today);
		if(currentMonthDay.equals(birthday)) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}
	@Test
	public void test6() {
		LocalTime time = LocalTime.now(); 
		System.out.println("local time now:" + time);
		
	}
	@Test
	public void test7() {
		LocalTime time  = LocalTime.now();
		LocalTime newTime = time.plusHours(2);
		System.out.println("Time after 2 hours :" + newTime);
		
		System.out.println("Time  :" + time.of(0, 0, 0));
	}
	@Test
	public void test8() {
		LocalDate date = LocalDate.now();
		LocalDate newDate = date.plusWeeks(1);
		System.out.println("Date after 1 weeks :" + newDate);
	}
	@Test
	public void test9() {
		Clock clock = Clock.systemUTC();
		System.out.println("Clock:" + clock);
		clock = clock.systemDefaultZone();
		System.out.println("Clock:" + clock);
	}
	@Test
	public void test10() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = LocalDate.of(2016, 1, 29);
		if(tomorrow.isAfter(today)) {
			System.out.println("tomorrow comes after today");
		}
		LocalDate yesterday = today.minusDays(1);
		if(yesterday.isBefore(today)) {
			System.out.println("yesterday is day before today");
		}
	}
	@Test
	public void test11() {
		ZoneId america = ZoneId.of("America/New_York");
		LocalDateTime localDateAndTime = LocalDateTime.now();
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateAndTime, america);
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork); 
	}
	@Test
	public void test12() {
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month yeari %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		YearMonth creditCardExpiry = YearMonth.of(2018, 1);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
	}
	@Test
	public void test13() {
		LocalDate today = LocalDate.now();
		if(today.isLeapYear()){
			System.out.println("This year is Leap year");
		}else {
			System.out.println("2014 is not a leap year");
		}
	}
	@Test
	public void test14() {
		LocalDate today = LocalDate.now();
		LocalDate java8Release = LocalDate.of(2014, 1, 1);
		Period periodTONextJavaRelea = Period.between(java8Release, today);
		System.out.println("Months left between today and Java 8 release : " + periodTONextJavaRelea.getYears() + ":" + periodTONextJavaRelea.getMonths() ); 
	}
	@Test
	public void test15() {
		LocalDateTime datetime = LocalDateTime.now(); 
		ZoneOffset offset = ZoneOffset.of("+05:30"); 
		OffsetDateTime date = OffsetDateTime.of(datetime, offset); 
		System.out.println("Date and Time with timezone offset in Java : " + date); 
	}
	/**
	 * 时间戳
	 */
	@Test
	public void test16() {
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant" + timestamp);
	}
	/**
	 * 日期格式
	 */
	@Test
	public void test17() {
		String dayAfterTommorrow = "20140116"; 
		LocalDate formatted = LocalDate.parse(dayAfterTommorrow, 
		DateTimeFormatter.BASIC_ISO_DATE); 
		System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
	}
	/**
	 * 自定义格式
	 */
	@Test
	public void test18() {
		String goodFriday = "8 18 2014"; 
		try { 
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M dd yyyy");     
		    LocalDate holiday = LocalDate.parse(goodFriday, formatter); 
		    System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday); 
		} catch (DateTimeParseException ex) { 
		    System.out.printf("%s is not parsable!%n", goodFriday); 
		    ex.printStackTrace(); 
		} 
	}
	/**
	 * 类型转换
	 */
	@Test
	public void test19() {
		LocalDateTime arrivalDate = LocalDateTime.now(); 
		try { 
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"); 
		    String landing = arrivalDate.format(format); 
		    System.out.printf("Arriving at : %s %n", landing); 
		    } catch (DateTimeException ex) { 
		    System.out.printf("%s can't be formatted!%n", arrivalDate); 
		    ex.printStackTrace(); 
		} 
	}
}

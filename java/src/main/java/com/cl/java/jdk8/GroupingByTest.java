package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.partitioningBy;

public class GroupingByTest {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("alice", "london", 200));
		employeeList.add(new Employee("Bob", "london", 150));
		employeeList.add(new Employee("charies", "New York", 160));
		employeeList.add(new Employee("Dorothy", "Hong Kong", 190));
		
		Map<String, List<Employee>> employeesByCity = employeeList.stream().collect(groupingBy(Employee::getCity));
		System.out.println(employeesByCity);
		Map<String, Long> numEmployeesByCity = employeeList.stream().collect(groupingBy(Employee::getCity, counting()));
		System.out.println(numEmployeesByCity);
		Map<String, Double> avgSalesByCity = employeeList.stream().collect(groupingBy(Employee::getCity, averagingDouble(Employee::getSales)));
		System.out.println(avgSalesByCity);
		Map<Boolean, List<Employee>> partitioned = employeeList.stream().collect(partitioningBy(e->e.getSales() > 150));
		System.out.println(partitioned);
		Map<Boolean, Map<String, Long>> result = employeeList.stream().collect(partitioningBy(e->e.getSales() > 150, groupingBy(Employee::getCity, counting())));
		System.out.println(result);
	}
	public static class Employee{
		private String name;
		private String city;
		private double sales;
		
		
		public Employee(String name, String city, double sales) {
			super();
			this.name = name;
			this.city = city;
			this.sales = sales;
		}

		public Employee(){
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public double getSales() {
			return sales;
		}

		public void setSales(double sales) {
			this.sales = sales;
		}
		
		
		
	}
}



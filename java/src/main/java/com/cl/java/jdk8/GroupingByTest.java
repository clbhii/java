package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Data;

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
	@Data
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			long temp;
			temp = Double.doubleToLongBits(sales);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Employee other = (Employee) obj;
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (Double.doubleToLongBits(sales) != Double.doubleToLongBits(other.sales))
				return false;
			return true;
		}


		

		
		
		
	}
}



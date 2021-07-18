package com.connection.oracle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Person {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String location;
	private Long salary; // we take Long here instead of long to check sarlary m null to ni hai
//	 {
//		// TODO Auto-generated method stub
//		return null;
//	}

	// public Person() // with connecting to DB we have to keep one default
	// constructor
	// {
	//
	// }
	//
	// public Person(String name, String location, long salary) {
	// this.name = name;
	// this.location = location;
	// this.salary = salary;
	// }

	// @Override
	// public String toString() {
	// return "Person [id=" + id + ", name=" + name + ", location=" + location + ",
	// salary=" + salary + "]";
	// }
}

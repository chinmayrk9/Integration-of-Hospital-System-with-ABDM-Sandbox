package com.example.HAD.patientregistration.bean;

import java.util.List;

public class Patient {
	private String id;
	private String name;
	private String gender;
	private String yearOfBirth;
	private String monthOfBirth;
	private String dayOfBirth;
	private Address address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getMonthOfBirth() {
		return monthOfBirth;
	}
	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}
	public String getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Identifiers> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(List<Identifiers> identifiers) {
		this.identifiers = identifiers;
	}
	private List<Identifiers> identifiers;

}

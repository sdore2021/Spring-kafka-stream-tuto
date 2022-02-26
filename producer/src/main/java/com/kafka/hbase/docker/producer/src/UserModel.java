package com.kafka.hbase.docker.producer.src;

public class UserModel {
	
	public String firstnane;
	public String lastname;
	public int age;
	
	public UserModel() {
	}
	
	public UserModel(String firstnane, String lastname, int age) {
		this.firstnane = firstnane;
		this.lastname = lastname;
		this.age = age;
	}
	
	public String getFirstnane() {
		return firstnane;
	}
	public void setFirstnane(String firstnane) {
		this.firstnane = firstnane;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserModel [firstnane=" + firstnane + ", lastname=" + lastname + ", age=" + age + "]";
	}
	
	
	
	
	
	

}

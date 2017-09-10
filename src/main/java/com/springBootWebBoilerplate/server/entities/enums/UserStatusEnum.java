package com.springBootWebBoilerplate.server.entities.enums;

public enum UserStatusEnum {
	SIMPLE("SIMPLE"),
	PAID("PAID");
	
	private String name;
	private UserStatusEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

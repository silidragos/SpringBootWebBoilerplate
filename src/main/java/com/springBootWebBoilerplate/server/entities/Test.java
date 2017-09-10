package com.springBootWebBoilerplate.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="test")
public class Test {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
}

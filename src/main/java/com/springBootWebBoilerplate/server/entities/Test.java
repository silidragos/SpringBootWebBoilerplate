package com.springBootWebBoilerplate.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity(name="test")
public class Test {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;

}

package com.springBootWebBoilerplate.server.entities.meta;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterPOSTRequest {
	String email;
	String password;
}

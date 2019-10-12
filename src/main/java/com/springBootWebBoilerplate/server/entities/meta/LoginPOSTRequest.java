package com.springBootWebBoilerplate.server.entities.meta;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginPOSTRequest {
	String email;
	String password;
}

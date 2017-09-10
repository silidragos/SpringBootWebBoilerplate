package com.springBootWebBoilerplate.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.springBootWebBoilerplate.server.entities.enums.UserStatusEnum;

@Entity
@Table(name="users")
@NamedQueries({
    @NamedQuery(name=User.FIND_BY_EMAIL,
            query="SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name=User.FIND_BY_UNIQUE_ID,
            query="SELECT u FROM User u WHERE u.uniqueId = :uuid")
}) 
public class User {
	public static final String FIND_BY_EMAIL = "findByEmail";
	public static final String FIND_BY_UNIQUE_ID = "findByUniqueId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name="unique_id")
	String uniqueId;

	@Column
	String email;
	
	@Column
	String password;

    @Enumerated(EnumType.STRING)
    UserStatusEnum status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
    
}

package com.fmalessio.twitterapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`User`")
public class User {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "first_login")
	private Date firstLogin;

	@Column(name = "last_login")
	private Date lastLogin;

	public User(String id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public User() {
		// Needed to JPA
	}

	@Override
	public String toString() {
		return "Id: " + id + ", Full name: " + fullName + ", First login: " + firstLogin + ", Last login: " + lastLogin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Date firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}

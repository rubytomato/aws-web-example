package com.example.web.aws.model;

import java.io.Serializable;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1595768734175347794L;

	public UserModel() {
	}

	public UserModel(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}

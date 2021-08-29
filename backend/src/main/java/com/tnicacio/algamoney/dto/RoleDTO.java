package com.tnicacio.algamoney.dto;

import com.tnicacio.algamoney.entities.Role;

import java.io.Serializable;

public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String authority;
	
	public RoleDTO() {
	}

	public RoleDTO(String id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public RoleDTO(Role role) {
		id = role.getId().toString();
		authority = role.getAuthority();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}

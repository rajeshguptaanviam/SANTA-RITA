package com.net.parking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Integer id;
	
	@Column(name="role")
	private String role;
	
	@Column(name="created_on", nullable = false)
	private String createdOn = "";
	
	@Column(name = "modifiedOn")
	private String modifiedOn = "";
	
	@Column(name = "createdBy", length = 100)
	private String createdBy = "DEV";
	
	@Column(name = "modifiedBy", length = 100)
	private String modifiedBy = "DEV";
	
	public Role() {}
	
	public Role(String role, String createdOn, String modifiedOn, String createdBy, String modifiedBy) {
		this.role = role;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}

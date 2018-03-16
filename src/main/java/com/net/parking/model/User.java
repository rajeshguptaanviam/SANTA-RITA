package com.net.parking.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email=? and u.password=? and u.status=1")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "U", strategy = "com.net.parking.model.generator.UserIDGenerator")
	@GeneratedValue(generator = "U")  
	@Column(name = "user_id", length = 255, nullable = false)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	@NotEmpty(message = "*Please provide your password")
	@Length(min = 8, message = "*Your password must have at least 8 characters")
	private String password;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your Email")
	private String email;
	
	@Column(name = "role")
	private String role = "DEV";
	
	@Column(name = "status")
	private Boolean status = false;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public User() {}
	
	public User(String name, String password, String email, String role, Boolean status) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}

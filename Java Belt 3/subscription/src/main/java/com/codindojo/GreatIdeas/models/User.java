package com.codindojo.GreatIdeas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "First name is required.")
	@Size(min = 3, message = "Name must be at least 3 characters")
	private String fName;

	@NotNull(message = "Last name is required.")
	@Size(min = 5, message = "Last Name must be at least 3 characters.")
	private String lName;

	@Email(message = "Must be a valid email.")
	private String email;

	@NotNull(message = "Password cannot be blank.")
	@Size(min = 5, message = "Password must be at least 5 characters.")
	private String password;

	@Transient // transient is a variables modifier used in serialization. At the time of
				// serialization, if we don’t want to save value of a particular variable in a
				// file or a database.
	private String passwordConfirm;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;


	
	@OneToMany(mappedBy="rater", fetch=FetchType.LAZY) // One way to read this is a show has --> many ratings
	private List<Dig> digs; // This will provide us an "array" or "List" of ratings

	@OneToMany(mappedBy="iCreator", fetch=FetchType.LAZY)
	private List<Idea> createdIdeas;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_ideas",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "idea_id")
	)
	private List<Idea> Ideas;


	public User() {}


	// These are needed for date manipulations. 
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}


	 // **** Getters and Setters **** 
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
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


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Dig> getDigs() {
		return digs;
	}


	public void setDigs(List<Dig> digs) {
		this.digs = digs;
	}


	public List<Idea> getCreatedIdeas() {
		return createdIdeas;
	}


	public void setCreatedIdeas(List<Idea> createdIdeas) {
		this.createdIdeas = createdIdeas;
	}


	public List<Idea> getIdeas() {
		return Ideas;
	}


	public void setIdeas(List<Idea> Ideas) {
		this.Ideas = Ideas;
	}



}

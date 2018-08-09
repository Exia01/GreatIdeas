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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Idea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer count;

	@NotNull(message = "Idea name cannot be empty!")
	@Size(min = 5, message = "Idea name must be at least 5 characters.")
	private String name;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_ideas", joinColumns = @JoinColumn(name = "idea_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> diggers;

	@OneToMany(mappedBy="rates",  fetch=FetchType.LAZY) // One way to read this is a show has --> many ratings
	private List<Dig> digs; // This will provide us an "array" or "List" of ratings


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User iCreator;


	public Idea() {
		this.count = 0;
	}

	public Idea(String name, User iCreator ) {
		this.name = name;
		this.iCreator = iCreator;
	}



// These are needed for date manipulations. 
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}


    /// **** Getters and Setters ***   
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<User> getDiggers() {
		return diggers;
	}

	public void setDiggers(List<User> diggers) {
		this.diggers = diggers;
	}

	public List<Dig> getDigs() {
		return digs;
	}

	public void setDigs(List<Dig> digs) {
		this.digs = digs;
	}

	public User getiCreator() {
		return iCreator;
	}

	public void setiCreator(User iCreator) {
		this.iCreator = iCreator;
	}
	
	
    

    
}

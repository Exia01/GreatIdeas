package com.codindojo.GreatIdeas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
public class Dig {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
    @Min(1)
	@Max(5)
	private Integer count;


	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
    private User rater;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="network_id")
	private Idea rates;
    
	public Dig() {
		this.count = 1;
	}

	public Dig(User rater, Idea rates) {
        this.rater = rater;
		this.rates = rates;
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


    // **** Getters and Setters **** 
	
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

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	public Idea getRates() {
		return rates;
	}

	public void setRates(Idea rates) {
		this.rates = rates;
	}

}

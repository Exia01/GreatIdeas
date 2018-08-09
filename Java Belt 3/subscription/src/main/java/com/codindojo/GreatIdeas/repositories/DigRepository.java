package com.codindojo.GreatIdeas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codindojo.GreatIdeas.models.Dig;

public interface DigRepository extends CrudRepository<Dig, Long> {
	List<Dig> findAll();

}


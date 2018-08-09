package com.codindojo.GreatIdeas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codindojo.GreatIdeas.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long> {
	List<Idea> findAll();

}


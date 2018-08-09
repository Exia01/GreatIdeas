package com.codindojo.GreatIdeas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codindojo.GreatIdeas.models.Idea;
import com.codindojo.GreatIdeas.models.User;
import com.codindojo.GreatIdeas.repositories.IdeaRepository;

@Service
public class IdeaService {
	private final IdeaRepository ideaRepository;
	
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}
	
	public List<Idea> getAllIdeas(){
		return ideaRepository.findAll();
	}

	public Idea createIdea(Idea ms, User u) {
		ms.setiCreator(u);
		return ideaRepository.save(ms);
	}
	
	public Idea getIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		if (optionalIdea.isPresent()) return optionalIdea.get();
		else return null;
	}
	
	public void updateIdea(Idea ms) {
			ideaRepository.save(ms);
	}
	
	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	
	}

	public void digIdea(Idea i, User u) {
		System.out.println("hit dig idea");
		List<User> diggers = i.getDiggers();
		System.out.println(diggers);
		diggers.add(u);
		System.out.println(u);
		System.out.println(diggers);
		i.setDiggers(diggers);
		ideaRepository.save(i);
	} 
	
	public void undigIdea(Idea i, User u) {
		List<User> diggers = i.getDiggers();
		diggers.remove(u);
		i.setDiggers(diggers);
		ideaRepository.save(i);
	}
}


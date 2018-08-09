package com.codindojo.GreatIdeas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codindojo.GreatIdeas.models.Idea;
import com.codindojo.GreatIdeas.models.Dig;
import com.codindojo.GreatIdeas.models.User;
import com.codindojo.GreatIdeas.repositories.DigRepository;

@Service
public class DigService {
    private final DigRepository digRepository;
	
	public DigService(DigRepository digRepository) {
		this.digRepository = digRepository;
	}
	

	public List<Dig> getAllRatings(){
		return digRepository.findAll();
	}
	
	public Dig createRating(Dig rt, User u, Idea ms){
        rt.setRater(u);
		rt.setRates(ms);
		
		return digRepository.save(rt);
	}
	
	public Dig getRating(Long id) {
		Optional<Dig> optionalRating = digRepository.findById(id);
		if (optionalRating.isPresent()) return optionalRating.get();
		else return null;
	}
	
	public void updateRating(Dig ms) {
			digRepository.save(ms);
	}
	
	public void deleteRating(Long id) {
		digRepository.deleteById(id);
	
	}
}




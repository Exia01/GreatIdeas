package com.codindojo.GreatIdeas.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codindojo.GreatIdeas.models.User;
import com.codindojo.GreatIdeas.repositories.UserRepository;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}


	public List<User> all(){
		return userRepository.findAll();
	}

	public User register(User user){
		User emailExists = (User) findByEmail(user.getEmail());

		if(emailExists != null){
			return null;
		}else{
			user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
			return userRepository.save(user);
		}
	}

	public User login(String email,String password){
		User emailExists = (User) findByEmail(email);
		
		if(emailExists != null) {
			if(BCrypt.checkpw(password, emailExists.getPassword() )){
				return emailExists;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public User findById(Long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) return u.get();
        else return null;
    }






}

package com.restapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.entities.User;
import com.restapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}
	@Override
	public User getUserById(Long id) {
		Optional<User> optionalusr=userRepo.findById(id);
		return optionalusr.get();
	}
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	@Override
	public User updateUser(User user) {
		User existingUser=userRepo.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updateUser=userRepo.save(existingUser);
		return updateUser;
	}
	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
}
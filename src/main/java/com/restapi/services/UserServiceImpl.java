package com.restapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.dto.UserDto;
import com.restapi.entities.User;
import com.restapi.exception.EmailAlreadyExistsException;
import com.restapi.exception.ResourceNotFoundException;
import com.restapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		//convert UserDto into user Jpa Entity
//		User user=new User(
//					userDto.getId(),
//					userDto.getFirstName(),
//					userDto.getLastName(),
//					userDto.getEmail()
//				);
//		User user=UserMapper.mapToUser(userDto);
		Optional<User> optionalUser=userRepo.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for user");
		}
		User user=modelMapper.map(userDto, User.class);
		
		User savedUser=userRepo.save(user);
		//convert user Jpa  entity to UserDto
//		UserDto savedUserDto=new UserDto(
//					savedUser.getId(),
//					savedUser.getFirstName(),
//					savedUser.getLastName(),
//					savedUser.getEmail()
//				);
//		UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto=modelMapper.map(savedUser, UserDto.class);
		
		return savedUserDto;
	}
	@Override
	public UserDto getUserById(Long id) {
//		Optional<User> optionalusr=userRepo.findById(id);
//		User user=optionalusr.get();
		User user=userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", id)
				);
//		return UserMapper.mapToUserDto(user);
		return modelMapper.map(user, UserDto.class);
	}
	@Override
	public List<UserDto> getAllUsers() {
		List<User> Users=userRepo.findAll();
//		return Users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		return Users.stream().map((user) -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
	}
	@Override
	public UserDto updateUser(UserDto userDto) {
//		User existingUser=userRepo.findById(userDto.getId()).get();
		User existingUser=userRepo.findById(userDto.getId()).orElseThrow(
					()-> new ResourceNotFoundException("User", "Id", userDto.getId())
				);
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		User updatedUser=userRepo.save(existingUser);
//		return UserMapper.mapToUserDto(updatedUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}
	@Override
	public void deleteUser(Long id) {
		User existingUser=userRepo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("User", "id", id)
				);
		userRepo.deleteById(id);
	}
}
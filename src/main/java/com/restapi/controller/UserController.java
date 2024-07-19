package com.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.dto.UserDto;
import com.restapi.entities.User;
import com.restapi.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userServ;
	
	//http://loclhost:8080/api/users
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
		UserDto savedUser=userServ.createUser(user);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	//http://localhost:8080/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto user=userServ.getUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users=userServ.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/users/1
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto userDto){
		userDto.setId(id);
		UserDto updateUser=userServ.updateUser(userDto);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	
	//http://localhost:8080/api/users/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		userServ.deleteUser(id);
		return new ResponseEntity<>("User successfully deleted !",HttpStatus.OK);
	}
	
}

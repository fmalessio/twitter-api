package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fmalessio.twitterapi.entity.User;
import com.fmalessio.twitterapi.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping
	@ResponseBody
	public String getUserInfo(@RequestParam(name = "id", required = true) String id) throws JsonProcessingException {
		return usersService.getUserInfo(id);
	}

	@PutMapping
	@ResponseBody
	public String login(@RequestBody User user) throws JsonProcessingException {
		User userLogged = usersService.login(user);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());

		return mapper.writeValueAsString(userLogged);
	}

}

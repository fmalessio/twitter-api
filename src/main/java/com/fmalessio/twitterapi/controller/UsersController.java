package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmalessio.twitterapi.entity.User;
import com.fmalessio.twitterapi.service.UsersService;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping
	@ResponseBody
	public String getUser(@RequestParam(name = "id", required = true) String id) throws JsonProcessingException {
		return usersService.getUserInfo(id);
	}

	@PostMapping
	@ResponseBody
	public String login(@RequestBody User user) throws JsonProcessingException {
		return usersService.login(user);
	}

}

package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmalessio.twitterapi.service.UsersService;

@Controller
public class ApplicationController {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/check-user-db")
	@ResponseBody
	public String checkDb(@RequestParam(name = "id", defaultValue = "test@test.com") String id)
			throws JsonProcessingException {
		return usersService.getUserInfo(id);
	}

}

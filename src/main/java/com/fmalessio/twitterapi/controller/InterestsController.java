package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fmalessio.twitterapi.entity.Interest;
import com.fmalessio.twitterapi.service.InterestsService;

@Controller
@RequestMapping("/interests")
public class InterestsController {

	@Autowired
	private InterestsService interestsService;

	@PostMapping
	@ResponseBody
	public void create(@RequestBody Interest interest) {
		interestsService.create(interest);
	}

}

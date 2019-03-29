package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fmalessio.twitterapi.entity.Interest;
import com.fmalessio.twitterapi.service.InterestsService;

@Controller
@RequestMapping("/interests")
public class InterestsController {

	@Autowired
	private InterestsService interestsService;

	@PostMapping
	@ResponseBody
	public String create(@RequestBody Interest interest) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());

		return mapper.writeValueAsString(interestsService.create(interest));
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public void delete(@PathVariable long id) {
		interestsService.delete(id);
	}

}

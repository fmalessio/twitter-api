package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fmalessio.twitterapi.service.TweetsService;

@Controller
@RequestMapping("/tweets-scheduler")
public class TweetsController {

	@Autowired
	private TweetsService tweetsService;

	@PostMapping
	@ResponseBody
	public void test() {
		tweetsService.runScheduler();
	}

	@DeleteMapping("/{name}")
	@ResponseBody
	public void testDelete(@PathVariable String name) {
		tweetsService.removeJob(name);
	}

}

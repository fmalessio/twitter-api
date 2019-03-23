package com.fmalessio.twitterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fmalessio.twitterapi.service.TweetsService;

@Controller
@RequestMapping("/tweets")
public class TweetsController {

	@Autowired
	private TweetsService tweetsService;

	@GetMapping("/test")
	@ResponseBody
	public void test() {
		tweetsService.getTweetsByHashtag("#river", 7);
	}

}

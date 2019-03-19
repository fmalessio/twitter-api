package com.fmalessio.twitterapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}

}

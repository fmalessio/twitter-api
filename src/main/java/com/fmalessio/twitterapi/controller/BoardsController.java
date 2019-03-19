package com.fmalessio.twitterapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.service.BoardsService;

@Controller
@RequestMapping("/boards")
public class BoardsController {

	@Autowired
	private BoardsService boardsService;

	@PostMapping
	@ResponseBody
	public void createBoard(@RequestBody Board board) throws JsonProcessingException {
		boardsService.createBoard(board);
	}

	@GetMapping("/{id}/interests")
	@ResponseBody
	public String getBoardInterests(@PathVariable long id) throws JsonProcessingException {
		Optional<Board> board = boardsService.getBoard(id);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());

		return mapper.writeValueAsString(board.get());
	}

}
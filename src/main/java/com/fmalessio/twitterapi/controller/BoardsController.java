package com.fmalessio.twitterapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.entity.SearchedTweet;
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

	@DeleteMapping("/{id}")
	@ResponseBody
	public void delete(@PathVariable long id) {
		boardsService.delete(id);
	}

	@GetMapping("/{id}/interests")
	@ResponseBody
	public String getBoardInterests(@PathVariable long id) throws JsonProcessingException {
		Optional<Board> board = boardsService.getBoard(id);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		if (board.isPresent()) {
			return mapper.writeValueAsString(board.get().getInterests());
		}
		return mapper.writeValueAsString("[]");
	}

	@GetMapping("/{id}/tweets")
	@ResponseBody
	public String getBoardTweets(@PathVariable long id, @RequestParam(name = "from", required = true) int from,
			@RequestParam(name = "to", required = true) int to) throws JsonProcessingException {
		List<SearchedTweet> tweets = boardsService.getAllSearcheadTweets(id);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		return mapper.writeValueAsString(tweets);
	}

}

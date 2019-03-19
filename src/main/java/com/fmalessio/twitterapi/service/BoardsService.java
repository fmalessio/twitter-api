package com.fmalessio.twitterapi.service;

import java.util.Optional;

import com.fmalessio.twitterapi.entity.Board;

public interface BoardsService {

	Optional<Board> getBoard(long id);

	void createBoard(Board board);

}

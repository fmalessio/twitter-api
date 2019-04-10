package com.fmalessio.twitterapi.service;

import java.util.List;
import java.util.Optional;

import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.entity.SearchedTweet;

public interface BoardsService {

	Optional<Board> getBoard(long id);

	Board createBoard(Board board);

	void delete(long id);

	List<SearchedTweet> getAllSearcheadTweets(long id, long lastSearched);

}

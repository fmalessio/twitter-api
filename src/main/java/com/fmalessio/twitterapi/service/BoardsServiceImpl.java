package com.fmalessio.twitterapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.entity.SearchedTweet;
import com.fmalessio.twitterapi.repository.BoardRepository;
import com.fmalessio.twitterapi.repository.SearchedTweetRepository;

@Service
public class BoardsServiceImpl implements BoardsService {

	private BoardRepository boardRepository;
	private SearchedTweetRepository searchedTweetRepository;

	@Autowired
	public BoardsServiceImpl(BoardRepository boardRepo, SearchedTweetRepository searchedTweetRepository,
			InterestsService interestsService) {
		this.boardRepository = boardRepo;
		this.searchedTweetRepository = searchedTweetRepository;
	}

	@Override
	public Board createBoard(Board board) {
		board = boardRepository.saveAndFlush(board);
		return board;
	}

	@Override
	public Optional<Board> getBoard(long id) {
		return boardRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		Optional<Board> board = boardRepository.findById(id);
		if (board.isPresent() && board.get().getInterests().isEmpty()) {
			boardRepository.deleteById(id);
		}
	}

	@Override
	public List<SearchedTweet> getAllSearcheadTweets(long id) {
		return searchedTweetRepository.findAllTweetsByBoard(id);
	}

}

package com.fmalessio.twitterapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.entity.Interest;
import com.fmalessio.twitterapi.entity.SearchedTweet;
import com.fmalessio.twitterapi.repository.BoardRepository;
import com.fmalessio.twitterapi.repository.SearchedTweetRepository;

@Service
public class BoardsServiceImpl implements BoardsService {

	private InterestsService interestsService;
	private BoardRepository boardRepository;
	private SearchedTweetRepository searchedTweetRepository;

	@Autowired
	public BoardsServiceImpl(BoardRepository boardRepo, SearchedTweetRepository searchedTweetRepository,
			InterestsService interestsService) {
		this.boardRepository = boardRepo;
		this.searchedTweetRepository = searchedTweetRepository;
		this.interestsService = interestsService;
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
		if (board.isPresent()) {
			List<Long> interestIdsOfBoard = board.get().getInterests().stream().map(Interest::getId)
					.collect(Collectors.toList());
			interestsService.deleteAllByIds(interestIdsOfBoard);
			// Delete board
			boardRepository.deleteById(id);
		}
	}

	@Override
	public List<SearchedTweet> getAllSearcheadTweets(long id) {
		// TODO: FROM, TO params
		return searchedTweetRepository.findAllTweetsByBoard(id);
	}

}

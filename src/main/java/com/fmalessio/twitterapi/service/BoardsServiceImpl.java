package com.fmalessio.twitterapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.entity.Board;
import com.fmalessio.twitterapi.repository.BoardRepository;

@Service
public class BoardsServiceImpl implements BoardsService {

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public void createBoard(Board board) {
		boardRepo.saveAndFlush(board);
	}

	@Override
	public Optional<Board> getBoard(long id) {
		return boardRepo.findById(id);
	}

	@Override
	public void delete(long id) {
		boardRepo.deleteById(id);
	}

}

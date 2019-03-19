package com.fmalessio.twitterapi.repository;

import org.springframework.stereotype.Repository;

import com.fmalessio.twitterapi.entity.Board;

@Repository
public interface BoardRepository extends MyBaseRepository<Board, Long> {

}

package com.fmalessio.twitterapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fmalessio.twitterapi.entity.SearchedTweet;

@Repository
public interface SearchedTweetRepository extends MyBaseRepository<SearchedTweet, Long> {

	static final String TWEETS_BY_BOARD = "SELECT searched_tweets.* from searched_tweets "
			+ "INNER JOIN interest on interest.id = searched_tweets.interest_id "
			+ "INNER JOIN board on board.id = interest.board_id " + "WHERE board.id = :boardId";

	@Query(value = TWEETS_BY_BOARD, nativeQuery = true)
	public List<SearchedTweet> findAllTweetsByBoard(@Param("boardId") long boardId);
}

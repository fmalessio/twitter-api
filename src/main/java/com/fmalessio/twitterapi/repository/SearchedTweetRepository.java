package com.fmalessio.twitterapi.repository;

import org.springframework.stereotype.Repository;

import com.fmalessio.twitterapi.entity.SearchedTweet;

@Repository
public interface SearchedTweetRepository extends MyBaseRepository<SearchedTweet, Long> {

}

package com.fmalessio.twitterapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.social.twitter.api.Tweet;

@Entity
@Table(name = "`searched_tweets`")
public class SearchedTweet {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "interest_id")
	private Long interestId;

	@Column(name = "text")
	private String text;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "from_user")
	private String fromUser;

	public SearchedTweet(Tweet tweet, long interestId) {
		this.id = tweet.getId();
		this.interestId = interestId;
		this.text = tweet.getUnmodifiedText();
		this.createdAt = tweet.getCreatedAt().getTime();
		this.fromUser = tweet.getUser().getName();
	}

	public SearchedTweet() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

}

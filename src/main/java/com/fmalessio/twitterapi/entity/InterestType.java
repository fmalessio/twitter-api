package com.fmalessio.twitterapi.entity;

public enum InterestType {
	HASHTAG("hashtag"), USER("user");

	private String label;

	private InterestType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
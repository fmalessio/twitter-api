package com.fmalessio.twitterapi.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fmalessio.twitterapi.entity.User;

@Repository
public interface UserRepository extends MyBaseRepository<User, String> {

	@Override
	Optional<User> findById(String id);

}

package com.rollingpaper.ggeujeogggeujeog.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdaptor implements UserRepository {

	private final UserMapper userMapper;

	@Override
	public void save(User user) {
		userMapper.save(user);
	}

	@Override
	public void delete(Long id) {
		userMapper.delete(id);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userMapper.findByEmail(email);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userMapper.findById(id);
	}
}

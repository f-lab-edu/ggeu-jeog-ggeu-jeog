package com.rollingpaper.ggeujeogggeujeog.user.domain;

import java.util.Optional;

public interface UserRepository {

	void save(User user);

	void delete(Long id);

	void update(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findById(Long id);
}

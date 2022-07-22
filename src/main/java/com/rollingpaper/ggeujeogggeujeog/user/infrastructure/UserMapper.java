package com.rollingpaper.ggeujeogggeujeog.user.infrastructure;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

@Repository
@Mapper
public interface UserMapper {
	void save(User user);
}

package com.spintech.testtask.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;

public interface ShowRepository extends CrudRepository<Show, Long> {

	Set<Show> findByUsersUnwatchedThisShow(User user);

}

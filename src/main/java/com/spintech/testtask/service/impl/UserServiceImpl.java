package com.spintech.testtask.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spintech.testtask.converter.ActorConverter;
import com.spintech.testtask.converter.ShowConverter;
import com.spintech.testtask.converter.UserConverter;
import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.dto.UserDto;

import com.spintech.testtask.entity.User;
import com.spintech.testtask.repository.UserRepository;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.tmdb.SpinTechTmdbApi;

import info.movito.themoviedbapi.TmdbApi;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private ActorConverter actorConverter;

	@Autowired
	private SpinTechTmdbApi spinTechTmdbApi;

	@Autowired
	private ShowConverter showConverter;

	@Autowired
	private TmdbApi tmdbApi;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDto registerUser(String email, String password) {
		User user = User.builder().email(email).password(password).build();
		if (Objects.nonNull(user)) {
			return null;
		}
		user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
		userRepository.save(user);
		return userConverter.converUserToUserDto(user);
	}

	@Override
	public UserDto findUser(String email, String password) {
		User foundUser = userRepository.findByEmail(email);
		if (Objects.nonNull(foundUser)) {
			if (passwordEncoder.matches(password, foundUser.getPassword())) {
				return userConverter.converUserToUserDto(foundUser);
			}
		}
		return null;
	}

	@Override
	public UserDto addFavActorsToUser(String email, String password, List<ActorDto> actorsToSet) {
		UserDto foundUser = this.findUser(email, password);
		if (Objects.nonNull(foundUser)) {
			List<ActorDto> favs = foundUser.getFavourite_actors();
			favs.addAll(actorsToSet);
			foundUser.setFavourite_actors(favs);
			User userToSave = userConverter.convertUserDtoToUser(foundUser);
			userRepository.save(userToSave);
			return foundUser;
		}
		return null;
	}

	@Override
	public UserDto delFavActorsFromUser(String email, String password) {
		UserDto foundUser = this.findUser(email, password);
		if (Objects.nonNull(foundUser)) {
			foundUser.setFavourite_actors(Collections.emptyList());
			User userToSave = userConverter.convertUserDtoToUser(foundUser);
			userRepository.save(userToSave);
			return foundUser;
		}
		return null;
	}

	@Override
	public UserDto markWatchedShowsForUser(String email, String password, List<ShowDto> showsToAdd) {
		UserDto foundUser = this.findUser(email, password);
		if (Objects.nonNull(foundUser)) {
			List<ShowDto> favourite_shows = foundUser.getFavourite_shows();
			favourite_shows.addAll(showsToAdd);
			foundUser.setFavourite_shows(favourite_shows);
			User userToSave = userConverter.convertUserDtoToUser(foundUser);
			userRepository.save(userToSave);
			return foundUser;
		}
		return null;
	}

	@Override
	public UserDto unMarkWatchedShowsForUser(String email, String password, List<ShowDto> showsToAdd) {
		UserDto foundUser = this.findUser(email, password);
		if (Objects.nonNull(foundUser)) {
			foundUser.setUnwatched_shows(Collections.emptyList());
			User userToSave = userConverter.convertUserDtoToUser(foundUser);
			userRepository.save(userToSave);
			return foundUser;
		}
		return null;
	}

}
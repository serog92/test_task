package com.spintech.testtask.controller;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spintech.testtask.converter.UserConverter;
import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.dto.UserDto;
import com.spintech.testtask.service.ShowService;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.tmdb.impl.SpinTechTmdbApiImpl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShowService showService;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private SpinTechTmdbApiImpl spinTechTmdbApi;

	@RequestMapping(value = "/register", method = POST)
	public ResponseEntity registerUser(
		@RequestParam String email,
		@RequestParam String password)
	{
		if (userService.registerUser(email, password) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/add_fav_actors", method = POST)
	public ResponseEntity addFavActorsToUser(
		@RequestParam String email,
		@RequestParam String password, @RequestParam String favActors)
	{
		List<String> favs = getTokensWithCollection(favActors);

		List<ActorDto> favActorsDtosList = favs.stream().map(actor -> ActorDto.builder().tmdb_id(Integer.valueOf(actor)).build()).collect(Collectors.toList());
		UserDto user = userService.addFavActorsToUser(email, password, favActorsDtosList);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/del_fav_actors", method = POST)
	public ResponseEntity del_FavActorsToUser(
		@RequestParam String email,
		@RequestParam String password)
	{
		UserDto user = userService.delFavActorsFromUser(email, password);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}


	@RequestMapping(value = "/markUnwatched", method = POST)
	public ResponseEntity markUnwatched(
		@RequestParam String email,
		@RequestParam String password,
		List<ShowDto> unwatched)
	{
		UserDto user = userService.findUser(email, password);
		//user.setUnwatched_shows(unwatched);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}


	@RequestMapping(value = "/get_unwatched_shows_with_fav_actor", method = GET)
	public ResponseEntity getUserUnwatchedShowsWithFavActors(
		@RequestParam String email,
		@RequestParam String password)
	{
		UserDto user = userService.findUser(email, password);
		List<ShowDto> shows = showService.getUserUnwatchedShowsWithFavActors(userConverter.convertUserDtoToUser(user));
		if (shows != null && !shows.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(shows);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	public List<String> getTokensWithCollection(String str) {
		return Collections.list(new StringTokenizer(str, ",")).stream()
			.map(token -> (String) token)
			.collect(Collectors.toList());
	}
}

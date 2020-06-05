package com.spintech.testtask.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spintech.testtask.converter.ShowConverter;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.repository.ShowRepository;
import com.spintech.testtask.service.ShowService;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.tmdb.impl.SpinTechTmdbApiImpl;


@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private UserService userService;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private SpinTechTmdbApiImpl spinTechTmdbApi;

	@Autowired
	private ShowConverter showConverter;

	public List<ShowDto> getUserUnwatchedShowsWithFavActors(User user) {
		Set<Show> shows = showRepository.findByUsersUnwatchedThisShow(user);
		List<Actor> actors = user.getFavourite_actors();
		return shows.stream()
			.distinct()
			.filter(show -> checkIfShowContainsSingleActor(show, actors))
			.map(show -> showConverter.convertShowtoShowDto(show))
			.collect(Collectors.toList());
	}

	private boolean checkIfShowContainsSingleActor(Show show, List<Actor> actors) {
		return show.getActors().stream().anyMatch(actor -> actors.contains(actors));
	}

}
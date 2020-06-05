package com.spintech.testtask.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spintech.testtask.converter.ActorConverter;
import com.spintech.testtask.converter.ShowConverter;
import com.spintech.testtask.converter.UserConverter;
import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.dto.UserDto;
import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;

@Service
public class UnWrapperService {

	@Autowired
	private ActorConverter actorConverter;

	@Autowired
	private ShowConverter showConverter;

	@Autowired
	private UserConverter userConverter;

	public List<Actor> getActorsListFromActorDtoList(List<ActorDto> actorDtos) {
		if (actorDtos != null) {
			return actorDtos.stream().map(actor -> actorConverter.convertActorDtoToActor(actor)).collect(
				Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<Show> getShowsListFromShowsDtoList(List<ShowDto> showDtos) {
		if (showDtos != null) {
			return showDtos.stream().map(showDto -> showConverter.convertShowDtoToShow(showDto)).collect(
				Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<ActorDto> getActorsDtoListFromActorList(List<Actor> actors) {
		if (actors != null) {
			return actors.stream().map(actor -> actorConverter.convertActorToActorDto(actor)).collect(
				Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<ShowDto> getShowsDtoListFromShowList(List<Show> shows) {
		if (shows != null) {
			return shows.stream().map(show -> showConverter.convertShowtoShowDto(show)).collect(
				Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<UserDto> getUserDtoListFromUserList(List<User> users) {
		if (users != null) {
			return users.stream()
				.map(user -> userConverter.converUserToUserDto(user))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public List<User> getUserListFromUserDtoList(List<UserDto> users) {
		if (users != null) {
			return users.stream()
				.map(user -> userConverter.convertUserDtoToUser(user))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
}
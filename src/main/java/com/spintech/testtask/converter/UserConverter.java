package com.spintech.testtask.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.dto.UserDto;
import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;

@Service
public class UserConverter {


	@Autowired
	private UnWrapperService unWrapperService;

	public UserDto converUserToUserDto(User user) {

		List<ActorDto> actorDtos = unWrapperService.getActorsDtoListFromActorList(user.getFavourite_actors());
		List<ShowDto> favs = unWrapperService.getShowsDtoListFromShowList(user.getFavourite_shows());
		List<ShowDto> unwatched = unWrapperService.getShowsDtoListFromShowList(user.getUnwatched_shows());

		return UserDto.builder()
			.email(user.getEmail())
			.password(user.getPassword())
			.favourite_actors(actorDtos)
			.favourite_shows(favs)
			.unwatched_shows(unwatched)
			.build();
	}

	public User convertUserDtoToUser(UserDto userDto) {

		List<Actor> fav = unWrapperService.getActorsListFromActorDtoList(userDto.getFavourite_actors());
		List<Show> unwatched = unWrapperService.getShowsListFromShowsDtoList(userDto.getUnwatched_shows());
		List<Show> favs = unWrapperService.getShowsListFromShowsDtoList(userDto.getFavourite_shows());

		return User.builder()
			.email(userDto.getEmail())
			.password(userDto.getPassword())
			.favourite_actors(fav)
			.favourite_shows(favs)
			.unwatched_shows(unwatched)
			.build();
	}
}

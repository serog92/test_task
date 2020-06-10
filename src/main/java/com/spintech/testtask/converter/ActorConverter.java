package com.spintech.testtask.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.UserDto;
import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.User;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonPeople;

@Service
public class ActorConverter {

	@Autowired
	private UserConverter userConverter;

	public Actor convertActorDtoToActor(ActorDto actorDto) {
		List<User> usersWhoAdd = actorDto.getUsers_added_this_actor_to_fav()
			.stream()
			.map(user -> userConverter.convertUserDtoToUser(user))
			.collect(Collectors.toList());
		return Actor.builder().name(actorDto.getName()).usersAddedThisActorToFav(usersWhoAdd).build();
	}

	public ActorDto convertActorToActorDto(Actor actor) {
		List<UserDto> usersWhoAdd = actor.getUsersAddedThisActorToFav()
			.stream()
			.map(user -> userConverter.converUserToUserDto(user))
			.collect(Collectors.toList());
		return ActorDto.builder().name(actor.getName()).users_added_this_actor_to_fav(usersWhoAdd).build();
	}
}

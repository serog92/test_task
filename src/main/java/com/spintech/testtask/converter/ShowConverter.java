package com.spintech.testtask.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;



@Service
public class ShowConverter {

	@Autowired
	private UserConverter userConverter;

	public ShowDto convertShowtoShowDto(Show show) {
		return ShowDto.builder().key(show.getTmdb_key()).build();
	}

	public Show convertShowDtoToShow(ShowDto show) {
		List<User> usersAddedThis = show.getUsers_added_this_show_to_fav()
			.stream()
			.map(userDto -> userConverter.convertUserDtoToUser(userDto))
			.collect(Collectors.toList());
		List<User> usersUnwatchedThis = show.getUsers_unwatched_this_show()
			.stream()
			.map(userDto -> userConverter.convertUserDtoToUser(userDto))
			.collect(Collectors.toList());
		return Show.builder().tmdb_key(show.getKey())
			.usersAddedThisShowToFav(usersAddedThis)
			.usersUnwatchedThisShow(usersUnwatchedThis).build();
	}
}

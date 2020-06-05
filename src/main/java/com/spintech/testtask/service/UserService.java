package com.spintech.testtask.service;

import java.util.List;

import com.spintech.testtask.dto.ActorDto;
import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.dto.UserDto;

public interface UserService {

	UserDto registerUser(String email, String password);

	UserDto findUser(String email, String password);

	UserDto addFavActorsToUser(String email, String password, List<ActorDto> actorsToSet);

	UserDto delFavActorsFromUser(String email, String password);

	UserDto markWatchedShowsForUser(String email, String password, List<ShowDto> showsToAdd);

	UserDto unMarkWatchedShowsForUser(String email, String password, List<ShowDto> showsToAdd);
}


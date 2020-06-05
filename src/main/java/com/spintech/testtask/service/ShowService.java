package com.spintech.testtask.service;

import java.util.List;

import com.spintech.testtask.dto.ShowDto;
import com.spintech.testtask.entity.User;

public interface ShowService {
	 List<ShowDto> getUserUnwatchedShowsWithFavActors(User user);
}

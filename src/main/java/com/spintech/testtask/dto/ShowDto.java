package com.spintech.testtask.dto;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {

	private MovieDb movieDb;

	int key;

	private List<UserDto> users_added_this_show_to_fav;

	private List<UserDto> users_unwatched_this_show;

}

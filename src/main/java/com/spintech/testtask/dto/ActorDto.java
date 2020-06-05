package com.spintech.testtask.dto;

import java.util.List;

import info.movito.themoviedbapi.model.people.PersonPeople;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {

	private String name;

	private PersonPeople info;

	private Integer tmdb_id;

	private List<UserDto> users_added_this_actor_to_fav;
}

package com.spintech.testtask.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String email;

	private String password;

	private List<ActorDto> favourite_actors;


	private List<ShowDto> favourite_shows;


	private List<ShowDto> unwatched_shows;
}

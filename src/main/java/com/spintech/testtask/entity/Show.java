package com.spintech.testtask.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String title;

	@Column(unique = true)
	private Integer tmdb_key;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@Column(name = "usersAddedThisShowToFav")
	private List<User> usersAddedThisShowToFav  = Collections.emptyList();;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@Column(name = "usersUnwatchedThisShow")
	private List<User> usersUnwatchedThisShow  = Collections.emptyList();;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@Column(name = "actors")
	private List<Actor> actors  = Collections.emptyList();;

}

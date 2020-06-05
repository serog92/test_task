package com.spintech.testtask.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
public class Actor {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;

	@Column(unique = true)
	private Integer tmdb_api;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
	@Column(name = "usersAddedThisActorToFav")
	private List<User> usersAddedThisActorToFav  = Collections.emptyList();;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "show_id", foreignKey = @ForeignKey(name = "fk_show_id"))
	@Column(name = "shows")
	private List<Show> shows  = Collections.emptyList();;

}

package com.spintech.testtask.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String email;

    private String password;

    @OneToMany(fetch= FetchType.EAGER, mappedBy = "usersAddedThisActorToFav")
    @Column(name = "fav_actors")
    private List<Actor> favourite_actors  = Collections.emptyList();;

    @OneToMany(fetch= FetchType.EAGER, mappedBy = "usersAddedThisShowToFav",cascade = CascadeType.ALL)
    @Column(name = "fav_shows")
    private List<Show> favourite_shows  = Collections.emptyList();;

    @OneToMany(fetch= FetchType.EAGER, mappedBy = "usersUnwatchedThisShow",cascade = CascadeType.ALL)
    @Column(name = "unwatched_shows")
    private List<Show> unwatched_shows = Collections.emptyList();

}
package com.spintech.testtask.service.tmdb;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonPeople;

public interface SpinTechTmdbApi {

	String popularTVShows();

	MovieDb getShowById(int key);

	PersonPeople getPersonById(int id);
}

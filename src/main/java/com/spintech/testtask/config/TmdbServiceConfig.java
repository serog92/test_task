package com.spintech.testtask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.movito.themoviedbapi.TmdbApi;

@Configuration
public class TmdbServiceConfig {

	@Value("${tmdb.apikey}")
	private String tmdbApiKey;

	@Bean
	public TmdbApi tmdbApi() {
		return new TmdbApi(tmdbApiKey);
	}
}

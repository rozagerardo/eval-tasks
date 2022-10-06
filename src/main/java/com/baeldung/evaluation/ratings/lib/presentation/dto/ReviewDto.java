package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.time.LocalDateTime;

public class ReviewDto {

	private final String description;

	private final Integer rating;

	private final LocalDateTime date;

	public ReviewDto(String description, Integer rating, LocalDateTime date) {
		super();
		this.description = description;
		this.rating = rating;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public Integer getRating() {
		return rating;
	}

	public LocalDateTime getDate() {
		return date;
	}
}

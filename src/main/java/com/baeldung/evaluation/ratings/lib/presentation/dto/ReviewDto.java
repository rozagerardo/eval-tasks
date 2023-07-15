package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.time.LocalDateTime;

/**
 *
 * @author rozagerardo
 */
public class ReviewDto {

    private final String description;

    private final Integer rating;

    private final String date;

    public ReviewDto(String description, Integer rating, String date) {
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

    public String getDate() {
        return date;
    }
}

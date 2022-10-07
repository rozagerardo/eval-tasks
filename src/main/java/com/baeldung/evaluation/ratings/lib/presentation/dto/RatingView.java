package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.util.function.Function;

/**
 *
 * @author rozagerardo
 */
public enum RatingView {

    PERCENTAGE((percentage) -> percentage.doubleValue()), STARS(percentage -> 0.05 * percentage);

    private final Function<Double, Double> fn;

    RatingView(Function<Double, Double> fn) {
        this.fn = fn;
    }

    public Double determineRating(Double percentage) {
        return this.fn.apply(percentage);
    }

}

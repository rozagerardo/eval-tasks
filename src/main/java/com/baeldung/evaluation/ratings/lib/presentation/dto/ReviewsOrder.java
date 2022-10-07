package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.util.Comparator;

/**
 *
 * @author rozagerardo
 */
public enum ReviewsOrder {

    NEWER((r1, r2) -> r2.getDate()
        .compareTo(r1.getDate())), OLDER(
            (r1, r2) -> r1.getDate()
                .compareTo(r2.getDate())), HIGHER(
                    (r1, r2) -> r2.getRating()
                        .compareTo(r1.getRating())), LOWER(
                            (r1, r2) -> r1.getRating()
                                .compareTo(r2.getRating()));

    private final Comparator<ReviewDto> comparator;

    ReviewsOrder(Comparator<ReviewDto> comparator) {
        this.comparator = comparator;
    }

    public Comparator<ReviewDto> getComparator() {
        return comparator;
    }
}

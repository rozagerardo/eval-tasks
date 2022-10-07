package com.baeldung.evaluation.ratings.lib.domain;

import java.util.List;

/**
 *
 * @author rozagerardo
 */
public interface Reviewable {

    public List<Review> getReviews();

    public void addReview(Review review);

}

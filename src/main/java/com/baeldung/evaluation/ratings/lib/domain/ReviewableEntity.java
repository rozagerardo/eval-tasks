package com.baeldung.evaluation.ratings.lib.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class ReviewableEntity implements Reviewable {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reviewableId")
	private List<Review> reviews;

	@Override
	public List<Review> getReviews() {
		return this.reviews;
	}

	@Override
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reviews);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewableEntity other = (ReviewableEntity) obj;
		return Objects.equals(reviews, other.reviews);
	}
}

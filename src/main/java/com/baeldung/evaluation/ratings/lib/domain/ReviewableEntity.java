package com.baeldung.evaluation.ratings.lib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class ReviewableEntity implements Reviewable, Serializable {

	private static final long serialVersionUID = 1622357429438514992L;

	private final String reviewableType = this.getClass().getName();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "reviewableId")
	@JoinColumns({ @JoinColumn(name = "reviewableId", referencedColumnName = "id"),
			@JoinColumn(name = "type", referencedColumnName = "reviewableType") })
	private List<Review> reviews = new ArrayList<>();

	@Override
	public List<Review> getReviews() {
		return this.reviews;
	}

	public String getReviewableType() {
		return reviewableType;
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

package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.util.List;

import com.baeldung.evaluation.ratings.lib.domain.Review;
import com.baeldung.evaluation.ratings.lib.domain.Reviewable;

public abstract class ReviewableDto<T extends Reviewable> {

	private ReviewData reviewData;

	protected ReviewableDto(T reviewableEntity) {
		this(reviewableEntity, ReviewsOrder.NEWER, RatingView.PERCENTAGE);
	}

	protected ReviewableDto(T reviewableEntity, ReviewsOrder reviewsOrder) {
		this(reviewableEntity, reviewsOrder, RatingView.PERCENTAGE);
	}

	protected ReviewableDto(T reviewableEntity, RatingView ratingView) {
		this(reviewableEntity, ReviewsOrder.NEWER, ratingView);
	}

	protected ReviewableDto(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView) {
		this.reviewData = new ReviewData(reviewableEntity, reviewsOrder, ratingView);
	}

	public ReviewData getReviewData() {
		return this.reviewData;
	}

	class ReviewData {

		private T reviewableEntity;

		private RatingView ratingView;

		private ReviewsOrder reviewsOrder;

		protected ReviewData(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView) {
			this.reviewableEntity = reviewableEntity;
			this.reviewsOrder = reviewsOrder;
			this.ratingView = ratingView;
		}

		public Double getRating() {
			final Double percentageRating = this.reviewableEntity.getReviews().stream().mapToInt(Review::getRating)
					.average().orElse(0);
			return ratingView.determineRating(percentageRating);
		}

		public String getRatingType() {
			return this.ratingView.name();
		}

		public List<ReviewDto> getReviews() {
			return reviewableEntity.getReviews().stream()
					.map(r -> new ReviewDto(r.getDescription(), r.getRating(), r.getDate()))
					.sorted(this.reviewsOrder.getComparator()).toList();
		}
	}
}

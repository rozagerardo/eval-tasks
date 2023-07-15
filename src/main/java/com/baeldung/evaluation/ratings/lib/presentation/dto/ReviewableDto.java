package com.baeldung.evaluation.ratings.lib.presentation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.baeldung.evaluation.ratings.lib.domain.Review;
import com.baeldung.evaluation.ratings.lib.domain.Reviewable;

/**
 *
 * @author rozagerardo
 */
public abstract class ReviewableDto<T extends Reviewable> {

    private ReviewData reviewData;


    private DateTimeFormatter dateFormatter;

    protected ReviewableDto(T reviewableEntity) {
        this(reviewableEntity, ReviewsOrder.NEWER, RatingView.PERCENTAGE, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    protected ReviewableDto(T reviewableEntity, ReviewsOrder reviewsOrder) {
        this(reviewableEntity, reviewsOrder, RatingView.PERCENTAGE, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    protected ReviewableDto(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView) {
        this(reviewableEntity, reviewsOrder, ratingView,  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    protected ReviewableDto(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView,  DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
        this.reviewData = new ReviewData(reviewableEntity, reviewsOrder, ratingView );



    }

    public ReviewData getReviewData() {
        return this.reviewData;
    }

    public String formatDate(LocalDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }


    class ReviewData {

        private T reviewableEntity;

        private RatingView ratingView;

        private ReviewsOrder reviewsOrder;


        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        private int currentPage;
        private int pageSize;
        private int totalPages;
        private int totalReviews;
        private List<ReviewDto> reviews;

        public int getCurrentPage() {
            return currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotalReviews() {
            return totalReviews;
        }



        protected ReviewData(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView) {
            this.reviewableEntity = reviewableEntity;
            this.reviewsOrder = reviewsOrder;
            this.ratingView = ratingView;
            this.reviews = reviewableEntity.getReviews().stream()
                    .map(r -> new ReviewDto(r.getDescription(), r.getRating(), formatDate(r.getDate())))
                    .sorted(reviewsOrder.getComparator())
                    .collect(Collectors.toList());
            this.currentPage = 1;
            this.pageSize = this.reviews.size();
            this.totalPages = 1;
            this.totalReviews = this.reviews.size();
        }

        protected ReviewData(T reviewableEntity, ReviewsOrder reviewsOrder, RatingView ratingView, int currentPage, int pageSize) {
            this.reviewableEntity = reviewableEntity;
            this.reviewsOrder = reviewsOrder;
            this.ratingView = ratingView;
            this.reviews = reviewableEntity.getReviews().stream()
                    .map(r -> new ReviewDto(r.getDescription(), r.getRating(), formatDate(r.getDate())))
                    .sorted(reviewsOrder.getComparator())
                    .collect(Collectors.toList());
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.totalReviews = this.reviews.size();
            this.totalPages = (int) Math.ceil((double) totalReviews / pageSize);
            this.reviews = getPaginatedReviews();
        }

        public List<ReviewDto> getReviews() {
            return reviews;
        }

        private List<ReviewDto> getPaginatedReviews() {
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalReviews);
            if (startIndex >= totalReviews) {
                return Collections.emptyList();
            } else {
                return reviews.subList(startIndex, endIndex);
            }
        }

        public Double getRating() {
            final Double percentageRating = this.reviewableEntity.getReviews()
                    .stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0);
            return ratingView.determineRating(percentageRating);
        }

        public String getRatingType() {
            return this.ratingView.name();
        }


    }


}

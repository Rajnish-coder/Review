package org.example.review.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long productId);
    boolean addReview(Long productId, Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
}

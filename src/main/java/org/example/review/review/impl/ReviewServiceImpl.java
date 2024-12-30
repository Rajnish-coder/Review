package org.example.review.review.impl;

import org.example.review.review.Review;
import org.example.review.review.ReviewRepository;
import org.example.review.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        System.out.println("Build trigger");
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews;
    }

    @Override
    public boolean addReview(Long productId, Review review) {
        if (productId != null && review != null){
            review.setProductId(productId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setProductId(updatedReview.getProductId());
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}

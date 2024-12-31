package org.example.review.service;

import org.example.review.review.Review;
import org.example.review.review.ReviewRepository;
import org.example.review.review.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        reviewService = new ReviewServiceImpl(reviewRepository);
    }

    @Test
    void getAllReviews() {
        Long productId = 1L;
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(new Review(1L, "Good", "Detailed description", 5, productId));
        mockReviews.add(new Review(2L, "Bad", "Short description", 2, productId));

        when(reviewRepository.findByProductId(productId)).thenReturn(mockReviews);

        List<Review> result = reviewService.getAllReviews(productId);

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The size of the result should match the mock data");
        verify(reviewRepository, times(1)).findByProductId(productId);
    }

    @Test
    void addReview() {
        Long productId = 1L;
        Review review = new Review(null, "Title", "Description", 4, null);

        when(reviewRepository.save(review)).thenReturn(review);

        boolean result = reviewService.addReview(productId, review);

        assertTrue(result);
        assertEquals(productId, review.getProductId());
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void getReview() {
    }

    @Test
    void updateReview() {
    }

    @Test
    void deleteReview() {
    }
}
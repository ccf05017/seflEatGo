package com.saul.springboot.selfDemo.applications;


import com.saul.springboot.selfDemo.domain.Review;
import com.saul.springboot.selfDemo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTests {

    private ReviewService reviewService;

    @Mock
    ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReivew() {

        Review review = Review.builder()
                .writer("poppo")
                .id(1L)
                .score(3)
                .build();

        given(reviewRepository.save(any())).willReturn(review);

        Review saved = reviewService.addReview(1L, review);

        assertThat(saved.getId(), is(1L));

        verify(reviewRepository).save(any());

    }

}

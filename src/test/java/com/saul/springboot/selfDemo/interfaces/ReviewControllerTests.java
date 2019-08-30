package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ReviewService;
import com.saul.springboot.selfDemo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReviewService reviewService;

    @Test
    public void create() throws Exception {
        Review review = Review.builder().id(1L).build();

        given(reviewService.addReview(any())).willReturn(review);

        mvc.perform(post("/restaurants/1/reviews")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"writer\": \"poppo\", \"score\": 3, \"description\": \"JMT\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().stringValues("Location", "/restaurants/1/reviews/1"));

        verify(reviewService).addReview(any());
    }

}

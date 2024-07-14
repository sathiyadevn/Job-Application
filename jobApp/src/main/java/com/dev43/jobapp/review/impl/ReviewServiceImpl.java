package com.dev43.jobapp.review.impl;

import com.dev43.jobapp.review.ReviewRepository;
import com.dev43.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

}

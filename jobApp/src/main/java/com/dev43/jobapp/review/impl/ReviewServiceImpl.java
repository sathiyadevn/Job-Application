package com.dev43.jobapp.review.impl;

import com.dev43.jobapp.review.Review;
import com.dev43.jobapp.review.ReviewRepository;
import com.dev43.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);     // custom method,JPA -> findByCompanyId() -> findBy(CompanyId)
    }
}

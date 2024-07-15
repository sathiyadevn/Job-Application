package com.dev43.jobapp.review.impl;

import com.dev43.jobapp.company.Company;
import com.dev43.jobapp.company.CompanyService;
import com.dev43.jobapp.review.Review;
import com.dev43.jobapp.review.ReviewRepository;
import com.dev43.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;          // imp

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);     // custom method,JPA -> findByCompanyId() -> findBy(CompanyId)
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);     //
        if (company != null) {
            review.setCompany(company);         // imp
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);

    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));     //
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);   // get review
            Company company = companyService.getCompanyById(companyId);     // get company

            review.setCompany(null);        // detach/update review
            companyService.updateCompanyById(companyId,company);    // detach/update company

            company.getReviews().remove(review);        // remove review from company
            reviewRepository.deleteById(reviewId);      // remove review in review
            return true;
        }
        return false;
    }
}

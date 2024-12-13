package org.example.demographql.domain.reviews.controller;

import org.example.demographql.domain.books.service.BookService;
import org.example.demographql.domain.reviews.entity.Review;
import org.example.demographql.domain.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Review> getReviewsByBookId(@Argument Long id) {
        return reviewService.findByBookId(id);
    }

    @MutationMapping
    public Review addReview(@Argument Long bookId, @Argument String content, @Argument Float rating) {
        Review newReview = new Review();
        newReview.setBook(bookService.findById(bookId).orElseThrow());
        newReview.setContent(content);
        newReview.setRating(rating);
        newReview.setCreatedDate(OffsetDateTime.now());

        return reviewService.saveReview(newReview);
    }

    @MutationMapping
    public Map<String, Boolean> deleteReviewById(@Argument Long reviewId) {
        reviewService.deleteById(reviewId);
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("success", true);

        return resultMap;
    }
}

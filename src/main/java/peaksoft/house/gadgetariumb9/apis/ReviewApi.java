package peaksoft.house.gadgetariumb9.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.house.gadgetariumb9.dto.request.review.AnswerRequest;
import peaksoft.house.gadgetariumb9.dto.request.review.ReviewRequest;
import peaksoft.house.gadgetariumb9.dto.response.review.ReviewGradeInfo;
import peaksoft.house.gadgetariumb9.dto.response.review.ReviewPagination;
import peaksoft.house.gadgetariumb9.dto.response.review.ReviewRatingResponse;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.services.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "Endpoints for managing and retrieving reviews for products.")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewApi {

  private final ReviewService service;

  @Operation(summary = "Rating of reviews", description = "Get the rating summary of reviews for a specific product..")
  @GetMapping("/rating")
  public ReviewRatingResponse countReviewsRating(@RequestParam  Long subProductId) {
    return service.countReviewsRating(subProductId);
  }

  @PermitAll
  @GetMapping("/get-all")
  @Operation(summary = "All reviews", description = "Get all reviews by subProduct id")
  public ReviewPagination getAllReview (@RequestParam Long id,
      @RequestParam int pageSize,
      @RequestParam int numberPage){
    return service.getAllReviews(id,pageSize,numberPage);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
  @Operation(summary = "Save review", description = "Leave a review if the user bought this product")
  public SimpleResponse saveReview (@RequestBody ReviewRequest reviewRequest){
    return service.saveReview(reviewRequest);
  }

  @DeleteMapping("/{review_id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Delete review", description = "Delete review by id")
  public SimpleResponse deleteReview (@PathVariable Long review_id){
    return service.deleteReview(review_id);
  }

  @PutMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Update answer", description = "Update the answer to the question that the ADMIN left")
  public SimpleResponse updateAnswer (@RequestBody AnswerRequest answerRequest){
    return service.updateAnswer(answerRequest.getReviewId(),answerRequest.getReplyToComment());
  }

  @PostMapping("/reply")
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Reply to comment", description = "Admin answer to a comment left on this product")
  public SimpleResponse replyToComment (@RequestBody AnswerRequest answerRequest){
    return service.replyToComment(answerRequest);
  }

  @PermitAll
  @GetMapping
  @Operation(summary = "Get feedback", description = "Output of general statistics of reviews")
  public ReviewGradeInfo getFeedback(@RequestParam Long id){
    return service.getFeedback(id);
  }
}
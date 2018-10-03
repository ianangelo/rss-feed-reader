package org.ge.itappa.rest.controller;

import java.util.List;

import org.ge.itappa.domain.UserReviews;
import org.ge.itappa.rest.service.UserReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserReviewsController {
	
	@Autowired
	private UserReviewsService userReviewService;
	
	@RequestMapping(
			value = "/reviews",
			method = RequestMethod.GET,
			produces = { "application/json" }
			)
	public ResponseEntity<List<UserReviews>> getUSerReviews() {
		
		return userReviewService.getUserReviews();
	}
	
	@RequestMapping(
			value = "/submit-review",
			method = RequestMethod.POST,
			produces = { "application/json" }
			)
	public ResponseEntity<Void> postReview(UserReviews review) {
		
		HttpStatus status = userReviewService.postReview(review);
		
		return new ResponseEntity<>(null, status);
	}
	

}

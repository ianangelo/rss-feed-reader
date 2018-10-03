package org.ge.itappa.rest.service;

import java.util.List;

import org.ge.itappa.dao.UserReviewsDao;
import org.ge.itappa.domain.UserReviews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserReviewsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserReviewsService.class);
	
	@Autowired
	private UserReviewsDao dao;
	
	/**
	 * Retrieve all posted reviews
	 *
	 * @return
	 */
	public ResponseEntity<List<UserReviews>> getUserReviews(){
		
		try {
			
			return new ResponseEntity<>(dao.getReviews(), HttpStatus.OK);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Retrieve all posted reviews
	 *
	 * @param UserReviews
	 * @return
	 */
	public HttpStatus postReview(UserReviews review) {

		try {
			
			dao.addReview(review);
			return HttpStatus.OK;
			
		} catch(Exception e) {
			log.error(e.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
		
	}


}

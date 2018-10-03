package org.ge.itappa.dao;

import java.util.List;

import org.ge.itappa.domain.UserReviews;
import org.ge.itappa.repository.UserReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserReviewsDao {
	
	@Autowired
	private UserReviewsRepository repository;
	
	public List<UserReviews> getReviews() {
		return repository.findAll();
	}
	 
	public void addReview(UserReviews review) {
		repository.save(review);
	}
	

}

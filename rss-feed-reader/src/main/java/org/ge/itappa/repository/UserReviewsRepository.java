package org.ge.itappa.repository;

import java.util.List;

import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.domain.UserReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewsRepository extends JpaRepository<UserReviews, Long> {
	
	List<FeedItem> findByUsernameAndReviewContentContaining(String username, String review);
}


package org.ge.itappa.repository;

import java.util.List;

import org.ge.itappa.domain.FeedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSSFeedRepository extends JpaRepository<FeedItem, Long> {
	
	List<FeedItem> findByLinkAndTitle(String link, String title);
}


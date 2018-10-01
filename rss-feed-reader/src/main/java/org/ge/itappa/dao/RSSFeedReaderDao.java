package org.ge.itappa.dao;

import java.util.List;

import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.repository.RSSFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RSSFeedReaderDao {
	
	@Autowired
	private RSSFeedRepository repository;
	
	public List<FeedItem> getFeed() {
		return repository.findAll();
	}
	 
	public void addFeedItem(FeedItem item) {
		repository.save(item);
	}
	
	public List<FeedItem> getFeedByTitleAndUri(String uri, String title) {
		return repository.findByLinkAndTitle(uri, title);
	}

}

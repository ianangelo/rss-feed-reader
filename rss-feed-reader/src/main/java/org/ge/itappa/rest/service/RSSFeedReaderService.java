package org.ge.itappa.rest.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.ge.itappa.dao.RSSFeedReaderDao;
import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.util.RSSFeedReaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Service
public class RSSFeedReaderService {
	
	@Autowired
	private RSSFeedReaderUtils util;
	
	@Autowired
	private RSSFeedReaderDao dao;
	
	private static final Logger log = LoggerFactory.getLogger(RSSFeedReaderService.class);
	private static final String RSS_SOURCE = "https://www.nasa.gov/rss/dyn/mission_pages/kepler/news/kepler-newsandfeatures-RSS.rss";
	
	/**
	 * Retrieve all feed item from database
	 *
	 * @return
	 */
	public ResponseEntity<List<FeedItem>> getFeedList(){
		
		try {
			SyndFeed feed = readRss();
			@SuppressWarnings("unchecked")   
			List<FeedItem> items = util.map(feed.getEntries());
			addFeed(items);
			
			return new ResponseEntity<>(dao.getFeed(), HttpStatus.OK);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Add feed item to DB
	 * 
	 * @param List<FeedItem>
	 * @return
	 */
	private void addFeed(List<FeedItem> items) {
		items.forEach(
				item -> {
					if (dao.getFeedByTitleAndUri(item.getLink(), item.getTitle()).isEmpty()) {
						dao.addFeedItem(item);
					} 
				});
	}

	/**
	 * Parse RSS
	 *
	 * @return
	 */
	private SyndFeed readRss() throws FeedException, IOException {
		
		URL url = new URL(RSS_SOURCE);
		SyndFeedInput input = new SyndFeedInput();
		return input.build(new XmlReader(url));
	}
	

}

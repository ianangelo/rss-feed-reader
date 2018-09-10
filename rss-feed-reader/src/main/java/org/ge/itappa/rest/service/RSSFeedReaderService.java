package org.ge.itappa.rest.service;

import java.io.IOException;
import java.net.MalformedURLException;
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
	
	private final String rssSource = "https://www.nasa.gov/rss/dyn/mission_pages/kepler/news/kepler-newsandfeatures-RSS.rss";
	
	public ResponseEntity<List<FeedItem>> getFeedList(){
		
		try {
			SyndFeed feed = readRss();
			@SuppressWarnings("unchecked")   
			List<FeedItem> items = util.map(feed.getEntries());
			addFeed(items);
			//items.forEach(consumer);
			return new ResponseEntity<>(items, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	private void addFeed(List<FeedItem> items) {
		
/*
		items.forEach(new Consumer<FeedItem>() {
			public void accept(FeedItem item) {
				if (dao.addFeedItem(item)) {
					log.info("RSSFeedReaderService.addFeed() New feed item was added");
				}
				else {
					log.error("RSSFeedReaderService.addFeed() Error on adding item "+ item.getId());
				}
			}
		}); */
		
		for (FeedItem item : items) {
			if (dao.addFeedItem(item)) {
				log.info("RSSFeedReaderService.addFeed() New feed item was added");
			}
			else {
				log.error("RSSFeedReaderService.addFeed() Error on adding item "+ item.getId());
			}
				
		}
	}

	private SyndFeed readRss() throws MalformedURLException, FeedException, IOException {
		
		URL url = new URL(rssSource);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(url));
		return feed;
	}

}

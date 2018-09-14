package org.ge.itappa.rest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.ge.itappa.dao.RSSFeedReaderDao;
import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.util.RSSFeedReaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
	
	private final String rssSource = "https://www.nasa.gov/rss/dyn/mission_pages/kepler/news/kepler-newsandfeatures-RSS.rss";
	
	public ResponseEntity<List<FeedItem>> getFeedList(){
		
		try {
			SyndFeed feed = readRss();
			@SuppressWarnings("unchecked")   
			List<FeedItem> items = util.map(feed.getEntries());
			addFeed(items);
			
			return new ResponseEntity<>(dao.getFeed(), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	private void addFeed(List<FeedItem> items) {
		items.forEach(
				item -> {
					if (dao.getFeedByTitleAndUri(item.getLink(), item.getTitle()).isEmpty()) {
						dao.addFeedItem(item);
					} 
				});
	}

	private SyndFeed readRss() throws MalformedURLException, FeedException, IOException {
		
		URL url = new URL(rssSource);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(url));
		return feed;
	}
	
	 @Async
	 public void callRssReader() throws InterruptedException {
		 try {
			SyndFeed feed = readRss();
			Thread.sleep(1000L); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }

}

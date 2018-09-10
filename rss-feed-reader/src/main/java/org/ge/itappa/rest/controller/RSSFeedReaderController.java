package org.ge.itappa.rest.controller;

import java.util.List;

import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.rest.service.RSSFeedReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RSSFeedReaderController {
	
	//private final String rssSource = "https://www.nasa.gov/rss/dyn/mission_pages/kepler/news/kepler-newsandfeatures-RSS.rss";
	
	@Autowired
	private RSSFeedReaderService readerService;
	
	
	@RequestMapping(
			value = "/rss",
			method = RequestMethod.GET,
			produces = { "application/json" }
			)
	public ResponseEntity<List<FeedItem>> getRSS() {
		return readerService.getFeedList();
	}
	
	
//	@RequestMapping(
//			value = "/rss/{sub}", 
//			  method = RequestMethod.GET,
//			  produces = { "application/json"}
//			)
//	public SyndFeed getRSS(@PathVariable("sub") String sub){
//		
//		String subUrl = "https://www.nasa.gov/rss/dyn/mission_pages/kepler/news/kepler-newsandfeatures-RSS.rss";//"https://reddit.com/r/technology.rss";//+sub;//constant.
//		try {
//			URL url = new URL(subUrl);
//			SyndFeedInput input = new SyndFeedInput();
//	        SyndFeed feed = input.build(new XmlReader(url));
//	        
//	        //Mapper mapper = new DozerBeanMapper();
//	        
//	      
//	        
//	        //item = mapper.map(feed.getEntries().get(0), FeedItem.class);
//	        
//	        //feed.getEntries();
//	        
//	        return feed;
//			//FeedFe
//			//SyncFeed feed =
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FeedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			//TODO: HTTP Status -- subreddit not found
//			e.printStackTrace();
//			//return "Error found: sub "+sub+" not found";
//		}
//		
//		return null;
//	}
//
}

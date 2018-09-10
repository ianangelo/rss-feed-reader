package org.ge.itappa.util;

import java.util.function.Consumer;

import org.ge.itappa.dao.RSSFeedReaderDao;
import org.ge.itappa.domain.FeedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * Java 8 Consumer for forEach
 */
@Component
public class FeedItemConsumerImpl implements Consumer<FeedItem> {

	private static final Logger log = LoggerFactory.getLogger(FeedItemConsumerImpl.class);
	
	@Override
	public void accept(FeedItem item) {
		log.info("Feed Item: "+ item.getId());
	}
	
	public void add(FeedItem item, RSSFeedReaderDao dao) {
		if (dao.addFeedItem(item)) {
			log.info("RSSFeedReaderService.addFeed() New feed item was added");
		}
		else {
			log.error("RSSFeedReaderService.addFeed() Error on adding item "+ item.getId());
		}
	}

}

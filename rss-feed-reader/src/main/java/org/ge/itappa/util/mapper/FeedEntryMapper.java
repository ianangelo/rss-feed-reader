package org.ge.itappa.util.mapper;

import org.ge.itappa.domain.FeedItem;

import com.sun.syndication.feed.synd.SyndEntryImpl;

public class FeedEntryMapper {

	public FeedItem map(SyndEntryImpl entry) {
	
		FeedItem item = new FeedItem();
		
		item.setTitle(entry.getTitle());
		item.setDescription(entry.getDescription().getValue());
		item.setLink(entry.getLink());
		//item.setEnclosures(entry.getEnclosures());
		item.setUpdatedDate(entry.getPublishedDate());
		
		return item;
	}

}

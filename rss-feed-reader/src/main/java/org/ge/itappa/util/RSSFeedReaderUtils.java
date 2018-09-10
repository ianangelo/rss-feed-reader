package org.ge.itappa.util;

import java.util.List;
import java.util.stream.Collectors;

import org.ge.itappa.domain.FeedItem;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntryImpl;

@Component
public class RSSFeedReaderUtils {
	
	
	public List<FeedItem> map(List<SyndEntryImpl> entries) {
		return entries.stream()
				.map(from -> map(from))
				.collect(Collectors.toList());
	}
	
	public FeedItem map(SyndEntryImpl entry) {
	
		FeedItem item = new FeedItem();
		
		item.setTitle(entry.getTitle());
		//TODO: truncate to desc1, desc2 ...
		item.setDescription(truncate(entry.getDescription().getValue(), 10));
		item.setLink(entry.getLink());
		//item.setEnclosures(entry.getEnclosures());
		item.setUpdatedDate(entry.getPublishedDate());
		
		return item;
	}
	
	public static String truncate(String value, int length)
	{
	  if (value != null && value.length() > length)
	    value = value.substring(0, length);
	  return value;
	}
	

}

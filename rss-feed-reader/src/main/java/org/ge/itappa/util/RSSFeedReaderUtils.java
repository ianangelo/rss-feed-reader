package org.ge.itappa.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.ge.itappa.domain.FeedItem;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntryImpl;

@Component
public class RSSFeedReaderUtils {
	
	private final String REGEX_PATTERN = "[^.!?\\\\s][^.!?]*(?:[.!?](?!['\\\"]?\\\\s|$)[^.!?]*)*[.!?]?['\\\"]?(?=\\\\s|$)";
	private final int TEXT_LIMIT = 250;
	private final int TEXT_START_INDEX = 0;
	
	public List<FeedItem> map(List<SyndEntryImpl> entries) {
		return entries.stream()
				.map(from -> map(from))
				.collect(Collectors.toList());
	}
	
	public FeedItem map(SyndEntryImpl entry) {
	
		FeedItem item = new FeedItem();
		
		item.setTitle(entry.getTitle());
		item.setLink(entry.getLink());
		item.setUpdatedDate(entry.getPublishedDate());
		item.setZonedDateRetrieved(getCurrentLocalDate());
		mapDescription(item, splitToSentence(entry.getDescription().getValue()));
		
		return item;
	}
	
	public List<String> splitToSentence(String desc){
		Pattern pattern = Pattern.compile(REGEX_PATTERN, Pattern.MULTILINE | Pattern.COMMENTS);
		Matcher matcher = pattern.matcher(desc);
		
		List<String> descLines = new ArrayList<>();
		while (matcher.find()) {
		    descLines.add(matcher.group().substring(TEXT_START_INDEX, Math.min(matcher.group().length(), TEXT_LIMIT)));
		}
		
		return descLines;
	}
	
	public void mapDescription(FeedItem item, List<String> desc) {
		// normalize this:
		if (desc.size() > 0) {
			item.setDescription1(desc.get(0));
		}
		
		if (desc.size() > 1) {
			item.setDescription2(desc.get(1));
		}
		
		if (desc.size() > 2) {
			item.setDescription3(desc.get(2));
		}
		
	}
	
	public String getCurrentLocalDate () {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			    .append(DateTimeFormatter.ISO_DATE_TIME)
			    .appendLiteral(':')
			    .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, false)
			    .appendLiteral('Z')
			    .toFormatter();
		LocalDateTime.now().format(formatter);
		ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("Europe/Tallinn"));
		return zonedDateTime.format(formatter);
	}
	
	

}

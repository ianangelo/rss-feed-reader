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
public class UserRegistrationController {
	
	@Autowired
	private RSSFeedReaderService readerService;
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.GET,
			produces = { "application/json" }
			)
	public ResponseEntity<List<FeedItem>> getRSS() {
		
		return readerService.getFeedList();
	}
	
}

package org.ge.itappa.rest.controller;

import org.ge.itappa.domain.FeedItem;
import org.ge.itappa.rest.service.RSSFeedReaderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@RestController
public class UserRegistrationController
{
	//~ Instance fields --------------------------

	/**  */
	@Autowired
	private RSSFeedReaderService readerService;
	//~ Methods ----------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	@RequestMapping(
		value = "/register",
		method = RequestMethod.GET,
		produces = { "application/json" }
	)
	public ResponseEntity<List<FeedItem>> register()
	{
		return readerService.getFeedList();
	}
}

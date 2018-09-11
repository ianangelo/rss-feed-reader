package org.ge.itappa.rssfeedreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.ge.itappa.RssFeedReaderApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RssFeedReaderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
public class RssFeedReaderApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(RssFeedReaderApplicationTests.class);

	@LocalServerPort
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
	}
	
	@Test
	public void testControllerGet() {
		Response response = RestAssured.when().get("/rss");
		String jsonResponse = response.asString();
		log.info(jsonResponse);
		
		assertNotNull(jsonResponse);
		assertEquals("Expected: 200 HTTP Status", HttpStatus.OK.value(), response.statusCode());
		
	}

}

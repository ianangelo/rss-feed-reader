package org.ge.itappa.util;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

public class RSSFeedReaderTimer {
	
	private int delay = 1000;
	
	@Autowired
	Timer timer;
	
	public RSSFeedReaderTimer(int seconds) {
		timer.schedule(new TriggerRssReader(), seconds * delay);
	}
	
	class TriggerRssReader extends TimerTask {

		@Override
		public void run() {
			
		}
		
	}

}

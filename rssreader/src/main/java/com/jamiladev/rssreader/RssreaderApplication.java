package com.jamiladev.rssreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RssreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RssreaderApplication.class, args);

		System.setProperty("http.agent", "Chrome");

		Feed feed = new RssReader("http://www.thehindu.com/news/feeder/default.rss").readFeed();
		List<FeedMessage> feeds = feed.entries;
		for(FeedMessage message : feeds) {
			System.out.println(message);
		}
	}

}

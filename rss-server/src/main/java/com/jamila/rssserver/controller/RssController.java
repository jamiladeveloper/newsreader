package com.jamila.rssserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamila.rssserver.models.Category;
import com.jamila.rssserver.models.Configuration;
import com.jamila.rssserver.models.RssFeed;
import com.jamila.rssserver.parser.RSSFeedParser;
import com.jamila.rssserver.repositories.CategoryRepository;
import com.jamila.rssserver.repositories.ConfigurationRepository;
import com.jamila.rssserver.repositories.RssFeedRepository;
import com.jamila.rssserver.util.DateUtil;
import com.jamila.rssserver.web.res.Feed;
import com.jamila.rssserver.web.res.ResponseBody;
import com.jamila.rssserver.web.res.RssFeedDTO;
import com.jamila.rssserver.web.res.RssItem;

@CrossOrigin(origins = "*")
@RestController
public class RssController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ConfigurationRepository configurationRepository;

	@Autowired
	RssFeedRepository rssFeedRepository;

	@GetMapping("/categories/all")
	public ResponseBody<List<Category>> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return new ResponseBody<List<Category>>("200", "Categories fetched successfully", categories);
	}

	@GetMapping("/rssfeed/all")
	public ResponseBody<List<RssItem>> getAllRssFeeds() {
		List<RssFeed> allRssFeeds = rssFeedRepository.findAll();
		List<Category> categorries = categoryRepository.findAll();

		Map<String, String> categoryMap = new HashMap<String, String>();
		categorries.stream().map(c -> categoryMap.put(c.getId(), c.getName()));
		List<RssFeedDTO> rssDTOs = allRssFeeds.stream()
				.map(r -> new RssFeedDTO(r.getId(), r.getCategoryId(), categoryMap.get(r.getId()), r.getRssLink()))
				.collect(Collectors.toList());
		
		RSSFeedParser rssParser;
		List<RssItem> feeds = new ArrayList<RssItem>();
		for(RssFeedDTO dto : rssDTOs) {
			rssParser = new RSSFeedParser(dto.getRssLink());
			Feed feed = rssParser.readFeed(dto.getCategoryName());
			feeds.addAll(feed.getMessages());
		}
		
		Collections.sort(feeds, new Comparator<RssItem>() {

			@Override
			public int compare(RssItem o1, RssItem o2) {
				if(DateUtil.ConvertToDate(o1.getPubDate()).after(DateUtil.ConvertToDate(o2.getPubDate()))) {
					return -1;
				}
				if(DateUtil.ConvertToDate(o1.getPubDate()).before(DateUtil.ConvertToDate(o2.getPubDate()))) {
					return 1;
				}
				return 0;
			}
		});

		return new ResponseBody<List<RssItem>>("200", "Successfully fetched " + feeds.size() + " feeds", feeds);
	}

	@GetMapping(value = "/configuration/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBody<Configuration> getLatestConfiguration() {
		List<Configuration> configs = configurationRepository.findAll();

		if (configs.size() == 1) {
			return new ResponseBody<Configuration>("200", "Coonfiguration fetched successfully", configs.get(0));
		}

		Configuration latestConfig = null;
		Long timestamp = 0L;
		for (int indx = 0; indx < configs.size(); indx++) {
			if (Long.parseLong(configs.get(indx).getTimestamp()) > timestamp) {
				timestamp = Long.parseLong(configs.get(indx).getTimestamp());
				latestConfig = configs.get(indx);
			}
		}

		return new ResponseBody<Configuration>("200", "Coonfiguration fetched successfully", latestConfig);

	}

	@GetMapping("/rssfeed")
	public ResponseBody<List<RssItem>> getRssByCategoryId(@RequestParam String id) {
		List<RssFeed> rssFeeds = rssFeedRepository.getRssByCategoryId(id);
		
		RSSFeedParser rssParser;
		List<RssItem> feeds = new ArrayList<RssItem>();
		for(RssFeed rssFeed : rssFeeds) {
			rssParser = new RSSFeedParser(rssFeed.getRssLink());
			Feed feed = rssParser.readFeed("");
			feeds.addAll(feed.getMessages());
		}
		
		Collections.sort(feeds, new Comparator<RssItem>() {

			@Override
			public int compare(RssItem o1, RssItem o2) {
				if(DateUtil.ConvertToDate(o1.getPubDate()).after(DateUtil.ConvertToDate(o2.getPubDate()))) {
					return -1;
				}
				if(DateUtil.ConvertToDate(o1.getPubDate()).before(DateUtil.ConvertToDate(o2.getPubDate()))) {
					return 1;
				}
				return 0;
			}
		});
		
		return new ResponseBody<List<RssItem>>("200", "Successfully fetched " + feeds.size() + " feeds", feeds);
	}

}

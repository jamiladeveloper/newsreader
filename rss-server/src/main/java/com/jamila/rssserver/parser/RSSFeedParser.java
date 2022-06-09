package com.jamila.rssserver.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.jamila.rssserver.util.DateUtil;
import com.jamila.rssserver.web.res.Feed;
import com.jamila.rssserver.web.res.RssItem;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String IMAGE = "image";
	static final String LINK = "link";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";

	final URL url;

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Feed readFeed(String categoryName) {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";
			String image = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language, copyright, image, guid, pubdate);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case IMAGE:
						image = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						RssItem message = new RssItem();
						message.setDescription(description);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						message.setPubDate(DateUtil.getDate(pubdate));
						message.setImage(image);
						message.setCategoryName(categoryName);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		StringBuffer sb = new StringBuffer();
		String startTag = "";
		if(event instanceof StartElement) {			
			startTag = event.asStartElement().getName().getLocalPart();
		}
		event = eventReader.nextEvent();
		while(event instanceof Characters) {
			sb.append(event.asCharacters().getData());
			event = eventReader.nextEvent();
		}
		if(event instanceof EndElement) {
			if(event.asEndElement().getName().getLocalPart().equalsIgnoreCase(startTag)){
				return sb.toString();
			}
		}
		return sb.toString();
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

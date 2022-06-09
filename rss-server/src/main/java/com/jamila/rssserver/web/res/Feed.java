package com.jamila.rssserver.web.res;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String image;
    final String pubDate;
    final String guid;

    final List<RssItem> entries = new ArrayList<RssItem>();

    public Feed(String title, String link, String description, String language,
            String copyright, String image, String guid, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.image = image;
        this.guid = guid;
        this.pubDate = pubDate;
    }

    public List<RssItem> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }
}

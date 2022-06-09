package com.jamila.rssserver.web.req;

import lombok.Getter;

@Getter
public class RequestBody<T> {
	T body;
}

package com.easylife.taobaoer.core.converter;

import java.nio.charset.Charset;
import java.util.Collections;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class MeilishuoJacksonHttpMessageConverter extends
		MappingJacksonHttpMessageConverter {
	public MeilishuoJacksonHttpMessageConverter() {
		setSupportedMediaTypes(Collections.singletonList(new MediaType("text",
				"html", Charset.forName("utf-8"))));
	}
}

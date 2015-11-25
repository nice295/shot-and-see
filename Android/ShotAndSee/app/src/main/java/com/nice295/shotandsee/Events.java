package com.nice295.shotandsee;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseFile;

@ParseClassName("Events")
public class Events extends ParseObject {
	
	public String getData() {
		return getString("Data");
	}
	
	public void setTitle(String title) {
		put("Data", title);
	}

	public ParseFile getImage() {
		return getParseFile("Image");
	}

	public String getImageUrl() {
		return getParseFile("Image").getUrl();
	}
	
	public static ParseQuery<Events> getQuery() {
		return ParseQuery.getQuery(Events.class);
	}
}

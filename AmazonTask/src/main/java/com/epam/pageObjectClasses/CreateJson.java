package com.epam.pageObjectClasses;

import com.google.gson.JsonObject;

public class CreateJson {
	public JsonObject  getJson(String arr[]) {
		JsonObject object = new JsonObject();
		JsonObject child1 = new JsonObject();
		JsonObject child2 = new JsonObject();
		JsonObject child3 = new JsonObject();

		object.addProperty("Second_highest_video_views", arr[0]);
		object.addProperty("Shortest_duration_video", arr[1]);
		object.addProperty("Shortest_duration_video_title", arr[2]);
		object.addProperty("Shortest_duration_video_views", arr[3]);
		
		object.addProperty("Highest_views_video_subscribers_count", arr[4]);
		object.addProperty("Highest_views_video_subscribers_likes_count", arr[4]);
		object.addProperty("Highest_views_video_subscribers_dislikes_count", arr[6]);
		//object.addProperty("Facebookurl", "Kargopolov");
		//object.addProperty("twitterURL", "Kargopolov");

		return object;
	}
}

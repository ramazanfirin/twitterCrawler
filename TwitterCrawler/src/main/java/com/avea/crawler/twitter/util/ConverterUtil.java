package com.avea.crawler.twitter.util;

import twitter4j.Status;
import twitter4j.User;

import com.avea.crawler.twitter.model.Twitt;
import com.avea.crawler.twitter.model.TwitterUser;

public class ConverterUtil {

	public static Twitt converToTwitt(Status status){
		Twitt twitt =  new Twitt();
	    twitt.setCreateDate(status.getCreatedAt());
	    twitt.setFavoriteCount(status.getFavoriteCount());
	    if(status.getGeoLocation()!=null)
	    	twitt.setLocation(status.getGeoLocation().toString());
	    twitt.setName("");
	    twitt.setRetweetCount(status.getRetweetCount());
	    twitt.setSource(status.getSource());
	    twitt.setText(status.getText());
	    twitt.setTwittID(status.getId());
	    
		return twitt;
	}
	
	public static TwitterUser converToTwitterUser(User user){
		TwitterUser twitterUser = new TwitterUser();
		twitterUser.setBigImageUrl(user.getBiggerProfileImageURL());
		twitterUser.setCreatedDate(user.getCreatedAt());
		twitterUser.setDescription(user.getDescription());
		twitterUser.setFallowersCouunt(user.getFollowersCount());
		twitterUser.setFaworitesCount(user.getFavouritesCount());
		twitterUser.setFriendsCount(user.getFriendsCount());
		twitterUser.setLocation(user.getLocation());
		twitterUser.setMiniImageUrl(user.getMiniProfileImageURL());
		twitterUser.setName(user.getName());
		twitterUser.setScreenName(user.getScreenName());
		twitterUser.setTimeZone(user.getTimeZone());
		twitterUser.setUrl(user.getURL());
		twitterUser.setUserID(user.getId());
		
		return twitterUser;
	}
	
}

package com.avea.test.crawler.twitter;

import java.util.Iterator;

import javax.xml.transform.stax.StAXSource;

import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class Test2 {
public static void main(String[] args) throws IllegalStateException, TwitterException {
	   
	Twitter twitter = TwitterFactory.getSingleton();
	
	//twitter.set
	System.out.println("id="+twitter.getId());
	
	IDs idList = twitter.getFollowersIDs(twitter.getId());
	
	User me = twitter.showUser("fuatavni");
	System.out.println("folowerCount = "+me.getFollowersCount());
	System.out.println("friend count = "+ me.getFriendsCount());
	System.out.println("lang"+ me.getLang());
	System.out.println("listed count = "+me.getListedCount());
	System.out.println("location="+me.getLocation());
	System.out.println("name = "+me.getName());
	System.out.println("screen name="+me.getScreenName());
	System.out.println("status="+me.getStatus());
	System.out.println("statusesCount"+me.getStatusesCount());
	
	
//	do {
//        if (0 < args.length) {
//            ids = twitter.getFollowersIDs(args[0], cursor);
//        } else {
//            ids = twitter.getFollowersIDs(cursor);
//        }
//        for (long id : ids.getIDs()) {
//            System.out.println(id);
//        }
//    } while ((cursor = ids.getNextCursor()) != 0);
	
	long cursor = -1;
	PagableResponseList<User> followers;
	do {
	     followers = twitter.getFollowersList("fuatavni", cursor);
	    for (User follower : followers) {
	        // TODO: Collect top 10 followers here
	        System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
	    }
	} while ((cursor = followers.getNextCursor()) != 0);
	
	cursor = -1;
	PagableResponseList<User> friends;
	do {
		friends = twitter.getFriendsList("fuatavni", cursor);
	    for (User follower : friends) {
	        // TODO: Collect top 10 followers here
	        System.out.println(follower.getName() + " friends " + follower.getFollowersCount() + " follower(s)");
	    }
	} while ((cursor = followers.getNextCursor()) != 0);
	
	
	
//	java.util.List<Status> statuses = null;
//	
//	
//	
//
//		statuses = twitter.getUserTimeline("fuatavni");
//		System.out.println("test");
//		Iterator<Status> iterator=statuses.iterator();
//		while(iterator.hasNext()){
//			Status status = iterator.next();
//			//System.out.println(status.getText()+" "+status.getSource()+" "+status.getCreatedAt());
//		}
			
	
}
}

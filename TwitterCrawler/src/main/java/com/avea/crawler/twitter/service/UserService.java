package com.avea.crawler.twitter.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

import com.avea.crawler.twitter.model.Twitt;
import com.avea.crawler.twitter.model.TwitterUser;
import com.avea.crawler.twitter.repository.UserRepository;
import com.avea.crawler.twitter.util.ConverterUtil;

@Service
public class UserService {

	@Autowired(required=true)
	private UserRepository userRepository;
	
	public void save(TwitterUser company){
		userRepository.save(company);
	}
	
	public void deleteAll(){
		userRepository.deleteAll();
	}
	
	public void updateLabels(String name){
		//companyRepository.updateLabelsForCompany();
		//companyRepository.updateLabelsForCountry();;
		
	}
	
	public Set<Twitt> getTwittsByUsername(Twitter twitter,String username) throws Exception{
		Set<Twitt> twitts = new HashSet<Twitt>();
		
		List<Status >statuses = twitter.getUserTimeline(username);
		Iterator<Status> iterator=statuses.iterator();
		while(iterator.hasNext()){
			Status status = iterator.next();
			Twitt temp = ConverterUtil.converToTwitt(status);
			twitts.add(temp);
		}
		
		return twitts;
	}
	
	public Set<TwitterUser> getFollowerList(Twitter twitter,String username) throws Exception {
		Set<TwitterUser> followerList = new HashSet<TwitterUser>();
		
		long cursor = -1;
		PagableResponseList<User> followers;
		do {
		     followers = twitter.getFollowersList(username, cursor);
		    for (User follower : followers) {
		        // TODO: Collect top 10 followers here
		        //System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
		    	TwitterUser temp = ConverterUtil.converToTwitterUser(follower);
		    	followerList.add(temp);
		    }
		} while ((cursor = followers.getNextCursor()) != 0);
		
		
		return followerList;
	}
	
	public Set<TwitterUser> getFriendList(Twitter twitter,String username) throws Exception {
		Set<TwitterUser> friendList = new HashSet<TwitterUser>();
		
		long cursor = -1;
		PagableResponseList<User> followers;
		do {
		     followers = twitter.getFriendsList(username, cursor);
		    for (User follower : followers) {
		        // TODO: Collect top 10 followers here
		        //System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
		    	TwitterUser temp = ConverterUtil.converToTwitterUser(follower);
		    	friendList.add(temp);
		    }
		} while ((cursor = followers.getNextCursor()) != 0);
		
		
		return friendList;
	}
	
	public TwitterUser getUser(Twitter twitter,String username,boolean checkDB) throws Exception {
		   if(checkDB){
			   TwitterUser user1=userRepository.getUserByName(username);
			   if(user1!=null)
				   return user1;
		   }User user = twitter.showUser(username);
		   System.out.println(username + " icin data alindi." );
		   TwitterUser rootUser = ConverterUtil.converToTwitterUser(user);
		   rootUser.setFollowers(getFollowerList(twitter, username));
		   rootUser.setFollowings(getFriendList(twitter, username));
		   rootUser.setTwittes(getTwittsByUsername(twitter, username));
	
		   return rootUser;
	}
	
	public void updateLabels(){
		userRepository.updateLabelsForTwitt();
		userRepository.updateLabelsForUser();
	}
}



	
	
	
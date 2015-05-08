package com.avea.test.crawler.twitter;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.avea.crawler.twitter.model.TwitterUser;
import com.avea.crawler.twitter.service.UserService;

@ContextConfiguration(locations = "classpath:helloWorldContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InsertTest {

	
	@Autowired(required=true)
	private UserService userService;
	
	Twitter twitter;
	
	@BeforeTransaction
	public void beforeTransaction() throws Exception{
		twitter = TwitterFactory.getSingleton();
	}
	
	
	@Rollback(false)
	@Ignore
	@Test
    public void deleteAll() throws Exception{
		userService.deleteAll();
		System.out.println("bitti");
	}
	
	@Rollback(false)
	//@Test
	public void testFuatAvni()throws Exception{
		 TwitterUser rootUser = userService.getUser(twitter, "fuatavni",true);
		 userService.save(rootUser);
	}
	
	@Rollback(false)
	//@Ignore
	@Test
    public void insert() throws Exception{
	   userService.deleteAll();
		String username = "ramazan_firin";	
	  
	   
	   TwitterUser rootUser = userService.getUser(twitter, username,true);
	   userService.save(rootUser);
	   
	   TwitterUser rootUser2 = userService.getUser(twitter, "tansudasli",false);
	   
	   for (Iterator iterator = rootUser2.getFollowings().iterator(); iterator.hasNext();) {
		   TwitterUser type = (TwitterUser) iterator.next();
		   if(type.getScreenName().equals("ramazan_firin")){
			   rootUser2.getFollowings().remove(type);
			   break;
		   }
			   
		
	   }
	   for (Iterator iterator = rootUser2.getFollowers().iterator(); iterator.hasNext();) {
		   TwitterUser type = (TwitterUser) iterator.next();
		   if(type.getScreenName().equals("ramazan_firin")){
			   rootUser2.getFollowers().remove(type);
			   break;
		   }
			   
		
	   }
	   userService.save(rootUser2);
	   
//	   for (Iterator iterator = rootUser.getFollowings().iterator(); iterator.hasNext();) {
//		   TwitterUser temp = (TwitterUser) iterator.next();
//		   TwitterUser user = userService.getUser(twitter, temp.getName());
//		   userService.save(user);
//	   }
	  
	   /*
	   for (Iterator iterator = rootUser.getFollowings().iterator(); iterator.hasNext();) {
		   TwitterUser temo = (TwitterUser) iterator.next();
		   TwitterUser user = userService.getUser(twitter, temo.getName());
		   userService.save(user);
	   }
	  
	   for (Iterator iterator = rootUser.getFollowers().iterator(); iterator.hasNext();) {
		   TwitterUser temo = (TwitterUser) iterator.next();
		   TwitterUser user = userService.getUser(twitter, temo.getScreenName());
		   userService.save(user);
		   System.out.println(temo.getName() + " bitti ");
	   }
	   */
	   
	  userService.updateLabels();
	  System.out.println("bitti");
		
	}
	
	
	
	
	@Rollback(false)
	@Ignore
	@Test
    public void updateLabels() throws Exception{
		userService.updateLabels("Product");
		System.out.println("bitti label");
	}
}

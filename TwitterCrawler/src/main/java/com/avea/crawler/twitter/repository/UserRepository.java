package com.avea.crawler.twitter.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.avea.crawler.twitter.model.TwitterUser;


public interface UserRepository extends GraphRepository<TwitterUser>{
	//Company save(Company company);
	
		
	@Query("START n=node(*) where n.__type__='TwitterUser' SET n:TwitterUser ;")
    public void updateLabelsForUser();
	
	@Query("START n=node(*) where n.__type__='Twitt' SET n:Twitt ;")
    public void updateLabelsForTwitt();
	
	@Query("START n=node(*) where n.screenName={0} return n;")
    public TwitterUser getUserByName(String name);
	
	
}

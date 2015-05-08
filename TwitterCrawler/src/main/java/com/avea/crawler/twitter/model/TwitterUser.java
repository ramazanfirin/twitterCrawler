package com.avea.crawler.twitter.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.neo4j.graphdb.Direction;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NoArgsConstructor
@NodeEntity
@TypeAlias(value="TwitterUser")
public @Data class TwitterUser extends AbstractEntity{

	@Indexed(unique = true)
	private String name;
	
	@Indexed
	private String screenName;
	
	@Indexed
	private Long userID;
	
private String bigImageUrl;
private Date createdDate;
private String description;
private int faworitesCount;
private int fallowersCouunt;
private int friendsCount;
private String location;
private String miniImageUrl;
private String timeZone;
private String url;

@RelatedTo(type="following",direction = Direction.OUTGOING)
private Set<TwitterUser> followings = new HashSet<TwitterUser>();

@RelatedTo(type="followedBy",direction = Direction.INCOMING)
private Set<TwitterUser> followers = new HashSet<TwitterUser>();

@RelatedTo(type="twitt",direction = Direction.OUTGOING)
private Set<Twitt> twittes = new HashSet<Twitt>();
}

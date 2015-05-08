package com.avea.crawler.twitter.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NoArgsConstructor
@NodeEntity
@TypeAlias(value="Twitt")
public @Data class Twitt extends AbstractEntity{

	@Indexed
	private String name;
	
	@Indexed(unique = true)
	private Long twittID;
	
	
	
	private int favoriteCount;
	private int retweetCount;
	private String source;
	private String text;
	private Date createDate;
	private String location;
}

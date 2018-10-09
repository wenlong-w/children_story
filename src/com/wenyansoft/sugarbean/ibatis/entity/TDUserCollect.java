package com.wenyansoft.sugarbean.ibatis.entity;

import java.io.Serializable;

public class TDUserCollect implements Serializable {
	private Integer id;
	private String openId;
	private String storyIds;
	
	public TDUserCollect(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getStoryIds() {
		return storyIds;
	}

	public void setStoryIds(String storyIds) {
		this.storyIds = storyIds;
	}

}

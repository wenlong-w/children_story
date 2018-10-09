package com.wenyansoft.everyday.ibatis.entry;

import java.io.Serializable;
import java.util.Date;

public class StoryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6901199341588282860L;
	
	private Integer id;
	private String storyKey;
	private String storyValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStoryKey() {
		return storyKey;
	}
	public void setStoryKey(String storyKey) {
		this.storyKey = storyKey;
	}
	public String getStoryValue() {
		return storyValue;
	}
	public void setStoryValue(String storyValue) {
		this.storyValue = storyValue;
	}
	
}

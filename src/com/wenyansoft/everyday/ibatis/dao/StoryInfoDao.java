package com.wenyansoft.everyday.ibatis.dao;

import com.wenyansoft.everyday.ibatis.entry.StoryInfo;

public interface StoryInfoDao {
	
	public StoryInfo findByKey(String key);
	
}

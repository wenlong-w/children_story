package com.wenyansoft.everyday.ibatis.dao;

import java.util.List;

import com.wenyansoft.everyday.ibatis.entry.StoryInfo;

public interface DiaryDao {
	
	void save(StoryInfo diary);
	public List<StoryInfo> findDiaryList();
	
}

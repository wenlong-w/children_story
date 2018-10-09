/**
 * 
 */
package com.wenyansoft.everyday.ibatis.dao;

import java.util.List;

import com.wenyansoft.everyday.ibatis.entry.Story;

/**
 * @author Administrator
 *
 */
public interface StoryDao {

	void save(Story story);
	
	public List<Story> findStoryList();
	
	public Story findStoryById(Integer id);
	
	public void updateStory(Story story);
	
}

/**
 * 
 */
package com.wenyansoft.ibatis.dao;

import java.util.List;

import com.wenyansoft.ibatis.entry.Story;

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

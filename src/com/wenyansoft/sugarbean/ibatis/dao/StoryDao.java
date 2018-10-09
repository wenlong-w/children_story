/**
 * 
 */
package com.wenyansoft.sugarbean.ibatis.dao;

import java.util.List;

import com.wenyansoft.sugarbean.ibatis.entity.TDStory;


/**
 * @author Administrator
 *
 */
public interface StoryDao {

	void save(TDStory story);
	
	public List<TDStory> findStoryList();
	
	public TDStory findStoryById(Integer id);
	
	public void updateStory(TDStory story);
	
}

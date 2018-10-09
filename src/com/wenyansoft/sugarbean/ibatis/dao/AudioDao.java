/**
 * 
 */
package com.wenyansoft.sugarbean.ibatis.dao;

import java.util.List;

import com.wenyansoft.sugarbean.ibatis.entity.Audio;


/**
 * @author Administrator
 *
 */
public interface AudioDao {

	void save(Audio audio);
	
	public List<Audio> findAudioList(String nameStr);
	
	public Audio findAudioById(Integer id);
	
	public void updateAudio(Audio audio);
	
}

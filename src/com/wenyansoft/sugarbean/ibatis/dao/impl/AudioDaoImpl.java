package com.wenyansoft.sugarbean.ibatis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.sugarbean.ibatis.dao.AudioDao;
import com.wenyansoft.sugarbean.ibatis.entity.Audio;


public class AudioDaoImpl implements AudioDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(Audio audio) {
		ibatisTemplate.insert("saveAudio", audio);
	}

	@Override
	public List<Audio> findAudioList(String nameStr) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nameStr", nameStr);
		List<Audio> stList = ibatisTemplate.queryForList("findAudioList",map);
		return stList;
	}

	@Override
	public Audio findAudioById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Audio audio = (Audio)ibatisTemplate.queryForObject("findAudioById",map);
		return audio;
	}


	@Override
	public void updateAudio(Audio audio) {
		// TODO Auto-generated method stub
		ibatisTemplate.update("updateAudioById", audio);
	}

	public SqlMapClientTemplate getIbatisTemplate() {
		return ibatisTemplate;
	}
	
	public void insertOpenId(){
		
	}

}

package com.wenyansoft.sugarbean.ibatis.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.sugarbean.ibatis.dao.StoryDao;
import com.wenyansoft.sugarbean.ibatis.entity.TDStory;

public class StoryDaoImpl implements StoryDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(TDStory story) {
		ibatisTemplate.insert("saveTDStory", story);
	}

	@Override
	public List<TDStory> findStoryList() {
		// TODO Auto-generated method stub
		List<TDStory> stList = ibatisTemplate.queryForList("findTDStoryList");
		return stList;
	}

	@Override
	public TDStory findStoryById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		TDStory story = (TDStory)ibatisTemplate.queryForObject("findTDStoryById",map);
		return story;
	}


	@Override
	public void updateStory(TDStory story) {
		// TODO Auto-generated method stub
		ibatisTemplate.update("updateTDStoryById", story);
	}

	public SqlMapClientTemplate getIbatisTemplate() {
		return ibatisTemplate;
	}
	
	public void insertOpenId(){
		
	}

}

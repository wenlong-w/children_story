package com.wenyansoft.ibatis.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.ibatis.dao.StoryDao;
import com.wenyansoft.ibatis.entry.Story;

public class StoryDaoImpl implements StoryDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(Story story) {
		ibatisTemplate.insert("saveStory", story);
	}

	@Override
	public List<Story> findStoryList() {
		// TODO Auto-generated method stub
		List<Story> stList = ibatisTemplate.queryForList("findStoryList");
		return stList;
	}

	@Override
	public Story findStoryById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Story story = (Story)ibatisTemplate.queryForObject("findStoryById",map);
		return story;
	}


	@Override
	public void updateStory(Story story) {
		// TODO Auto-generated method stub
		ibatisTemplate.update("updateStoryById", story);
	}

	public SqlMapClientTemplate getIbatisTemplate() {
		return ibatisTemplate;
	}
	
	public void insertOpenId(){
		
	}

}

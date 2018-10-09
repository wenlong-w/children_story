package com.wenyansoft.everyday.ibatis.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.everyday.ibatis.dao.StoryInfoDao;
import com.wenyansoft.everyday.ibatis.entry.StoryInfo;

public class StoryInfoDaoImpl implements StoryInfoDao {

	
private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public StoryInfo findByKey(String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storyKey", key);
		StoryInfo storyInfo = (StoryInfo)ibatisTemplate.queryForObject("findByKey",map);
		return storyInfo;
	}

}

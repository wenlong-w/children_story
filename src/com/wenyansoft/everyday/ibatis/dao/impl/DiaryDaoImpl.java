package com.wenyansoft.everyday.ibatis.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.everyday.ibatis.dao.DiaryDao;
import com.wenyansoft.everyday.ibatis.entry.StoryInfo;
import com.wenyansoft.everyday.ibatis.entry.Story;

public class DiaryDaoImpl implements DiaryDao {
	
private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(StoryInfo diary) {
		ibatisTemplate.insert("saveDiary", diary);
	}

	@Override
	public List<StoryInfo> findDiaryList() {
		// TODO Auto-generated method stub
		List<StoryInfo> diaryList = ibatisTemplate.queryForList("findDiaryList");
		return diaryList;
	}

}

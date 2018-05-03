package com.wenyansoft.ibatis.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.ibatis.dao.DiaryDao;
import com.wenyansoft.ibatis.entry.Diary;
import com.wenyansoft.ibatis.entry.Story;

public class DiaryDaoImpl implements DiaryDao {
	
private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(Diary diary) {
		ibatisTemplate.insert("saveDiary", diary);
	}

	@Override
	public List<Diary> findDiaryList() {
		// TODO Auto-generated method stub
		List<Diary> diaryList = ibatisTemplate.queryForList("findDiaryList");
		return diaryList;
	}

}

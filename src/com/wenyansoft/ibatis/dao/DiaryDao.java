package com.wenyansoft.ibatis.dao;

import java.util.List;

import com.wenyansoft.ibatis.entry.Diary;

public interface DiaryDao {
	
	void save(Diary diary);
	public List<Diary> findDiaryList();
	
}

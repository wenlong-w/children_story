package com.wenyansoft.bo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wenyansoft.ibatis.dao.DiaryDao;
import com.wenyansoft.ibatis.dao.StoryDao;
import com.wenyansoft.ibatis.dao.UserCollectDao;
import com.wenyansoft.ibatis.dao.UserInfoDao;

public class DaoFactory {
	private static DaoFactory instance;
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			new String[] {"spring-persist.xml"});

	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		if (instance == null) {
			synchronized (DaoFactory.class) {

				if (instance == null) {
					instance = new DaoFactory();
				}
			}
		}
		return instance;
	}
	
	public StoryDao getStoryDao() {
		return (StoryDao) ctx.getBean("storyDao");
	}
	
	public UserInfoDao getUserInfoDao() {
		return (UserInfoDao) ctx.getBean("userInfoDao");
	}
	
	public UserCollectDao getUserCollectDao() {
		return (UserCollectDao) ctx.getBean("userCollectDao");
	}
	
	public DiaryDao getDiaryDao() {
		return (DiaryDao) ctx.getBean("diaryDao");
	}
}

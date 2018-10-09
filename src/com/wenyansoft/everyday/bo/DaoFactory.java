package com.wenyansoft.everyday.bo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wenyansoft.everyday.ibatis.dao.DiaryDao;
import com.wenyansoft.everyday.ibatis.dao.StoryDao;
import com.wenyansoft.everyday.ibatis.dao.StoryInfoDao;
import com.wenyansoft.everyday.ibatis.dao.UserCollectDao;
import com.wenyansoft.everyday.ibatis.dao.UserInfoDao;

public class DaoFactory {
	private static DaoFactory instance;
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			new String[] {"spring-persist-everyday.xml"});

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
	
	public StoryInfoDao getStoryInfoDao() {
		return (StoryInfoDao) ctx.getBean("storyInfoDao");
	}
}

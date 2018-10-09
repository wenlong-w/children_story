package com.wenyansoft.sugarbean.bo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wenyansoft.sugarbean.ibatis.dao.StoryDao;
import com.wenyansoft.sugarbean.ibatis.dao.AudioDao;
import com.wenyansoft.sugarbean.ibatis.dao.UserCollectDao;
import com.wenyansoft.sugarbean.ibatis.dao.UserInfoDao;


public class DaoFactory {
	private static DaoFactory instance;
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			new String[] {"spring-persist-sugarbean.xml"});

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
	
	public AudioDao getAudioDao() {
		return (AudioDao) ctx.getBean("audioDao");
	}
	
	public UserCollectDao getUserCollectDao() {
		return (UserCollectDao) ctx.getBean("userCollectDao");
	}
}

package com.wenyansoft.ibatis.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.wenyansoft.ibatis.entry.Diary;
import com.wenyansoft.ibatis.entry.Story;

public class Test {
	
	
	public static void saveStory(){
		Story story = new Story();
		story.setStoryName("小黄莺唱歌");
		story.setPlayNum(2987);
		story.setPraiseNum(287);
		story.setContent("小黄莺唱歌");
		story.setDt(new Date());
		story.setImgUrl("http://image-1255464404.cosgz.myqcloud.com/%E5%B0%8F%E9%BB%84%E8%8E%BA%E5%94%B1%E6%AD%8C.jpg");
		story.setAudioUrl("http://audio-1255464404.cosgz.myqcloud.com/%E5%B0%8F%E9%BB%84%E8%8E%BA%E5%94%B1%E6%AD%8C.m4a");
		
		StoryService.getInstance().save(story);
	}
	
	public static void saveDiary(){
		Diary diary = new Diary();
		diary.setContent("今天是预产期，可是媳妇那边没啥动静。自己在家数胎动也都正常。看来宝宝想迟到啊。");
		diary.setGender("1");
		diary.setDt(new Date());
		System.out.println("999");
		DiaryService.getInstance().save(diary);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		saveStory();
		
		saveDiary();
		
		/*List<Story> storyList = StoryService.getInstance().findStoryList();
		for(int i=0; i<storyList.size(); i++){
			System.out.println(i+" "+storyList.get(i).getStoryName()+" "+storyList.get(i).getDt());
		}*/
		
		/*Story story = StoryService.getInstance().findStoryById(3);
		
		story.setImgUrl("http://image-1255464404.cosgz.myqcloud.com/212.png?sign=p4gkbBwGjeEW1e9wc8dFjyWD2phhPTEyNTU0NjQ0MDQmaz1BS0lEbzJERzl1dmp1VzJqTk9tRXVSUks0aEE5NnVnS2h3SGUmZT0xNTE4NzA1OTk4JnQ9MTUxNjExMzk5OCZyPTExOTg5ODg3MDYmZj0vMjEyLnBuZyZiPWltYWdl");
		StoryService.getInstance().updateStory(story);*/
		
	}

}

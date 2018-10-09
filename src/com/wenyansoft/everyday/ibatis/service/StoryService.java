package com.wenyansoft.everyday.ibatis.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wenyansoft.everyday.bo.DaoFactory;
import com.wenyansoft.everyday.ibatis.dao.StoryDao;
import com.wenyansoft.everyday.ibatis.entry.Story;

public class StoryService {
	
	
	protected Log log = LogFactory.getLog(getClass());
	private static StoryService instance;
	
	private StoryService(){}
	public static StoryService getInstance() {
		if (instance == null) {
			synchronized(StoryService.class){
				if (instance == null) {
					instance = new StoryService();
				}
			}
		}
		return instance;
	}
	
	public void save(Story story){
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		storyDao.save(story);
	}
	
	public void findStoryList (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		List<Story> storyList = storyDao.findStoryList();
		
		Map<String,Object> vaule = new HashMap<String,Object>(1);
		vaule.put("storyList", storyList);
		
		//���巵��json
		JSONObject jsonResult = new JSONObject();
		if (storyList != null) {
			jsonResult.put("result", true);
			jsonResult.put("message", "��ѯList�ɹ���");
			jsonResult.put("value", vaule);
		} else {
			jsonResult.put("result", false);
			jsonResult.put("message", "��ѯListʧ�ܣ�");
			jsonResult.put("value", "");
		}
		//����ֵ��ʶ
		//������ظ�ǰ̨
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	
	public Story findStoryById(Integer id){
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		Story story = storyDao.findStoryById(id);
		return story;
	}
	
	public void updateStory(Story story){
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		storyDao.updateStory(story);
	}
	
	/**
	 * ���Ź���
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void playAudio(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		Story story = storyDao.findStoryById(Integer.valueOf(id));
		story.setPlayNum(story.getPlayNum() + 1);
		storyDao.updateStory(story);
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		jsonResult.put("message", "����");
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * �û�����
	 * @param id
	 */
	public void doPraise(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		StoryDao storyDao = DaoFactory.getInstance().getStoryDao();
		Story story = storyDao.findStoryById(Integer.valueOf(id));
		story.setPraiseNum(story.getPraiseNum() + 1);
		storyDao.updateStory(story);
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		jsonResult.put("message", "���޳ɹ�");
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	
	
}

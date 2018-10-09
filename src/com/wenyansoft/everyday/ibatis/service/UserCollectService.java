package com.wenyansoft.everyday.ibatis.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wenyansoft.everyday.bo.DaoFactory;
import com.wenyansoft.everyday.ibatis.dao.UserCollectDao;
import com.wenyansoft.everyday.ibatis.entry.UserCollect;

public class UserCollectService {
	
	
	protected Log log = LogFactory.getLog(getClass());
	private static UserCollectService instance;
	
	private UserCollectService(){}
	public static UserCollectService getInstance() {
		if (instance == null) {
			synchronized(UserCollectService.class){
				if (instance == null) {
					instance = new UserCollectService();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 一进入某个故事，会检查听众的收藏故事的情况
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void findCollect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);

		String session = request.getParameter("session");
		HttpSession sessionTemp = request.getSession();
		String sessionFlag = (String) sessionTemp.getAttribute(session);
		if(sessionFlag==null){
			jsonResult.put("relogin", "yes");
		}else{
			String[] os = sessionFlag.split("_wenyan_");
			UserCollectDao userCollectDao = DaoFactory.getInstance().getUserCollectDao();
			UserCollect userCollect = userCollectDao.getUserCollect(os[0]);

			if(userCollect==null){
				jsonResult.put("value", "");
			}else{
				jsonResult.put("value", userCollect.getStoryIds());
			}
		}

		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	
	public void doCollect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		
		String session = request.getParameter("session");
		HttpSession sessionTemp = request.getSession();
		String key = (String) sessionTemp.getAttribute(session);
		
		if(key==null){
			jsonResult.put("relogin", "yes");
		}else{
			String[] keys =  key.split("_wenyan_");
			String storyIds = request.getParameter("storyIds");
			UserCollectDao userCollectDao = DaoFactory.getInstance().getUserCollectDao();
			UserCollect userCollect = userCollectDao.getUserCollect(keys[0]);
			if(userCollect==null){
				userCollect = new UserCollect();
				userCollect.setOpenId(keys[0]);
				userCollect.setStoryIds(storyIds);
				userCollectDao.save(userCollect);
			}else{
				userCollect.setStoryIds(storyIds);
				userCollectDao.update(userCollect);
			}
			jsonResult.put("message", "收藏成功");
		}
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
}

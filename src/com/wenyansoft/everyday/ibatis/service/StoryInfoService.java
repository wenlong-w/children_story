package com.wenyansoft.everyday.ibatis.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wenyansoft.everyday.bo.DaoFactory;
import com.wenyansoft.everyday.ibatis.dao.StoryInfoDao;
import com.wenyansoft.everyday.ibatis.entry.StoryInfo;

public class StoryInfoService {
	protected Log log = LogFactory.getLog(getClass());
	private static StoryInfoService instance;
	
	private StoryInfoService(){}
	
	public static StoryInfoService getInstance() {
		if (instance == null) {
			synchronized(StoryInfoService.class){
				if (instance == null) {
					instance = new StoryInfoService();
				}
			}
		}
		return instance;
	}
	
	public void findByKey (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StoryInfoDao storyInfoDao = DaoFactory.getInstance().getStoryInfoDao();
		String key = request.getParameter("key");
		StoryInfo storyInfo = storyInfoDao.findByKey(key);
		
		Map<String,Object> vaule = new HashMap<String,Object>(1);
		vaule.put("moreStory", storyInfo);
		
		//定义返回json
		JSONObject jsonResult = new JSONObject();
		if (storyInfo != null) {
			jsonResult.put("result", true);
			jsonResult.put("message", "查询信息成功！");
			jsonResult.put("value", vaule);
		} else {
			jsonResult.put("result", false);
			jsonResult.put("message", "查询信息失败！");
			jsonResult.put("value", "");
		}
		//返回值标识
		//结果返回给前台
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
}

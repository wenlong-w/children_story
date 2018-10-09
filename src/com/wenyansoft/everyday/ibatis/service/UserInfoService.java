package com.wenyansoft.everyday.ibatis.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wenyansoft.everyday.bo.DaoFactory;
import com.wenyansoft.everyday.commons.HttpRequest;
import com.wenyansoft.everyday.ibatis.dao.UserInfoDao;
import com.wenyansoft.everyday.ibatis.entry.UserInfo;
import com.wenyansoft.system.commons.StoryConstant;

public class UserInfoService {
	protected Log log = LogFactory.getLog(getClass());
	private static UserInfoService instance;
	
	private UserInfoService(){}
	public static UserInfoService getInstance() {
		if (instance == null) {
			synchronized(UserInfoService.class){
				if (instance == null) {
					instance = new UserInfoService();
				}
			}
		}
		return instance;
	}
	
	public void codeForSession (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/javascript; charset=utf-8");
		
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		String sessionFlag = code + System.currentTimeMillis();
		System.out.println("sessionFlag:"+sessionFlag);
		String session_key = "";
		String openid = "";
		String grant_type = "authorization_code";
		String params = "appid=" + StoryConstant.EVERYDAY_APPID + "&secret=" + StoryConstant.EVERYDAY_APPSECRET 
				+ "&js_code=" + code + "&grant_type=" + grant_type;
		String str = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
		
		System.out.println("返回的信息："+str);
		JSONObject json = JSONObject.fromObject(str);
		//用户的唯一标识（openid）
		if(json.containsKey("openid")){
			openid = (String) json.get("openid");
			
			UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
			UserInfo userInfo = userInfoDao.getUserInfo(openid);
			if(userInfo==null){
				userInfo = new UserInfo();
				userInfo.setOpenId(openid);
				userInfo.setDt(new Date());
				System.out.println("要新增的新用户:"+openid);
				log.info("要新增的新用户:"+openid);
				userInfoDao.addUserInfo(userInfo);
			}
		}
		//获取会话密钥（session_key）
		if(json.containsKey("session_key")){
			session_key = json.get("session_key").toString();
		}
		HttpSession sessionTemp = request.getSession();
		sessionTemp.setAttribute(sessionFlag, openid+"_wenyan_"+session_key);
		JSONObject jsonResult = new JSONObject();
		if(openid!="" && session_key!=""){
			jsonResult.put("session", sessionFlag);
			jsonResult.put("JSESSIONID", sessionTemp.getId());
			jsonResult.put("success", true);
		}else{
			jsonResult.put("session", "");
			jsonResult.put("success", false);
		}
		//返回值标识
		//结果返回给前台
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	public void find (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/javascript; charset=utf-8");
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		
		String session = request.getParameter("session");
		HttpSession sessionTemp = request.getSession();
		String key = (String) sessionTemp.getAttribute(session);
		if(key==null){
			jsonResult.put("relogin", "yes");
		}else{
			System.out.println("key:"+key);
			String[] keys =  key.split("_wenyan_");
			
			UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
			UserInfo userInfo = userInfoDao.getUserInfo(keys[0]);
			Date date = userInfo.getDt();
			System.out.println(date);
			jsonResult.put("message", "找到用户");
			jsonResult.put("value", date);
		}
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	public void save (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/javascript; charset=utf-8");
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		
		String session = request.getParameter("session");
		HttpSession sessionTemp = request.getSession();
		String key = (String) sessionTemp.getAttribute(session);
		if(key==null){
			jsonResult.put("relogin", "yes");
		}else{
			System.out.println("key:"+key);
			String[] keys =  key.split("_wenyan_");
			
			UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
			UserInfo userInfo = userInfoDao.getUserInfo(keys[0]);
			
			if(StringUtils.isNotBlank(request.getParameter("avatarUrl"))){
				userInfo.setAvatarUrl(request.getParameter("avatarUrl"));
			}
			if(StringUtils.isNotBlank(request.getParameter("city"))){
				userInfo.setCity(request.getParameter("city"));
			}
			if(StringUtils.isNotBlank(request.getParameter("country"))){
				userInfo.setCountry(request.getParameter("country"));
			}
			if(StringUtils.isNotBlank(request.getParameter("gender"))){
				userInfo.setGender(request.getParameter("gender"));
			}
			if(StringUtils.isNotBlank(request.getParameter("language"))){
				userInfo.setLanguage(request.getParameter("language"));
			}
			if(StringUtils.isNotBlank(request.getParameter("nickName"))){
				userInfo.setNick(URLDecoder.decode(request.getParameter("nickName"), "utf-8"));
			}
			if(StringUtils.isNotBlank(request.getParameter("province"))){
				userInfo.setProvince(request.getParameter("province"));
			}
			userInfoDao.update(userInfo);
			
			jsonResult.put("message", "保存成功");
		}
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
}

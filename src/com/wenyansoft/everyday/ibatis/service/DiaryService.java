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
import com.wenyansoft.everyday.ibatis.dao.DiaryDao;
import com.wenyansoft.everyday.ibatis.entry.StoryInfo;

public class DiaryService {
	protected Log log = LogFactory.getLog(getClass());
	private static DiaryService instance;
	
	private DiaryService(){}
	
	public static DiaryService getInstance() {
		if (instance == null) {
			synchronized(DiaryService.class){
				if (instance == null) {
					instance = new DiaryService();
				}
			}
		}
		return instance;
	}
	
	public void save(StoryInfo diary){
		DiaryDao diaryDao = DaoFactory.getInstance().getDiaryDao();
		diaryDao.save(diary);
	}
	
	public void findDiaryList (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DiaryDao diaryDao = DaoFactory.getInstance().getDiaryDao();
		List<StoryInfo> diaryList = diaryDao.findDiaryList();
		
		Map<String,Object> vaule = new HashMap<String,Object>(1);
		vaule.put("diaryList", diaryList);
		
		//���巵��json
		JSONObject jsonResult = new JSONObject();
		if (diaryList != null) {
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
}

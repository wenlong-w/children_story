package com.wenyansoft.sugarbean.ibatis.service;

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

import com.wenyansoft.sugarbean.bo.DaoFactory;
import com.wenyansoft.sugarbean.ibatis.dao.AudioDao;
import com.wenyansoft.sugarbean.ibatis.entity.Audio;

public class AudioService {
	
	protected Log log = LogFactory.getLog(getClass());
	private static AudioService instance;
	
	private AudioService(){}
	public static AudioService getInstance() {
		if (instance == null) {
			synchronized(AudioService.class){
				if (instance == null) {
					instance = new AudioService();
				}
			}
		}
		return instance;
	}
	
	public void save(Audio audio){
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		audioDao.save(audio);
	}
	
	/**
	 * audio�б���ʱ����ҳ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void findAudioList (HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		String nameStr = request.getParameter("nameStr");
		List<Audio> audioList = audioDao.findAudioList(nameStr);
		
		Map<String,Object> vaule = new HashMap<String,Object>(1);
		vaule.put("audioList", audioList);
		
		//���巵��json
		JSONObject jsonResult = new JSONObject();
		if (audioList != null) {
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
	
	public Audio findAudioById(Integer id){
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		Audio audio = audioDao.findAudioById(id);
		return audio;
	}
	
	public void updateAudio(Audio audio){
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		audioDao.updateAudio(audio);
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
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		Audio audio = audioDao.findAudioById(Integer.valueOf(id));
		audio.setPlayNum(audio.getPlayNum() + 1);
		audioDao.updateAudio(audio);
		
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
		AudioDao audioDao = DaoFactory.getInstance().getAudioDao();
		Audio audio = audioDao.findAudioById(Integer.valueOf(id));
//		audio.setPraiseNum(audio.getPraiseNum() + 1);
		audioDao.updateAudio(audio);
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", true);
		jsonResult.put("message", "���޳ɹ�");
		
		out.print(jsonResult.toString());
		out.flush();
		out.close();
	}
	
	
}

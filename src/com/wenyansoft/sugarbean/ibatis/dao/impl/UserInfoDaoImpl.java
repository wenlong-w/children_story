package com.wenyansoft.sugarbean.ibatis.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.sugarbean.ibatis.dao.UserInfoDao;
import com.wenyansoft.sugarbean.ibatis.entity.UserInfo;

public class UserInfoDaoImpl implements UserInfoDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		ibatisTemplate.insert("saveTDUserInfo", userInfo);
	}
	
	public UserInfo getUserInfo(String openid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openid);
		UserInfo userInfo = (UserInfo)ibatisTemplate.queryForObject("findTDUserInfoByOpenId",map);
		return userInfo;
	}
	
	@Override
	public void update(UserInfo userInfo) {
		// TODO Auto-generated method stub
		ibatisTemplate.update("updateTDUserInfo", userInfo);
	}

}

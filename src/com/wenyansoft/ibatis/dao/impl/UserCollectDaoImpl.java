package com.wenyansoft.ibatis.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.ibatis.dao.UserCollectDao;
import com.wenyansoft.ibatis.entry.UserCollect;
import com.wenyansoft.ibatis.entry.UserInfo;

public class UserCollectDaoImpl implements UserCollectDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(UserCollect userCollect) {
		ibatisTemplate.insert("saveUserCollect", userCollect);
	}
	
	public void update(UserCollect userCollect) {
		ibatisTemplate.update("update", userCollect);
	}

	@Override
	public UserCollect getUserCollect(String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openId);
		UserCollect userCollect = (UserCollect)ibatisTemplate.queryForObject("findUserCollectByOpenId",map);
		return userCollect;
	}

}

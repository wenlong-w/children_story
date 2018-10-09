package com.wenyansoft.sugarbean.ibatis.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.wenyansoft.sugarbean.ibatis.dao.UserCollectDao;
import com.wenyansoft.sugarbean.ibatis.entity.TDUserCollect;

public class UserCollectDaoImpl implements UserCollectDao {
	
	private Log log = LogFactory.getLog(getClass());
	
	private SqlMapClientTemplate ibatisTemplate;
	
	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) {
		this.ibatisTemplate = ibatisTemplate;
	}

	@Override
	public void save(TDUserCollect userCollect) {
		ibatisTemplate.insert("saveTDUserCollect", userCollect);
	}
	
	public void update(TDUserCollect userCollect) {
		ibatisTemplate.update("updateTDUserCollect", userCollect);
	}

	@Override
	public TDUserCollect getUserCollect(String openId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openid", openId);
		TDUserCollect userCollect = (TDUserCollect)ibatisTemplate.queryForObject("findTDUserCollect",map);
		return userCollect;
	}

}

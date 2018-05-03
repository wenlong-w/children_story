package com.wenyansoft.ibatis.dao;

import com.wenyansoft.ibatis.entry.UserCollect;

public interface UserCollectDao {

	void save(UserCollect userCollect);
	
	void update(UserCollect userCollect);
	
	public UserCollect getUserCollect(String openId);
}

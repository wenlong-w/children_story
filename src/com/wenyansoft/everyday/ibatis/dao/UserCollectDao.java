package com.wenyansoft.everyday.ibatis.dao;

import com.wenyansoft.everyday.ibatis.entry.UserCollect;

public interface UserCollectDao {

	void save(UserCollect userCollect);
	
	void update(UserCollect userCollect);
	
	public UserCollect getUserCollect(String openId);
}

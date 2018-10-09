package com.wenyansoft.sugarbean.ibatis.dao;

import com.wenyansoft.sugarbean.ibatis.entity.TDUserCollect;

public interface UserCollectDao {

	void save(TDUserCollect userCollect);
	
	void update(TDUserCollect userCollect);
	
	public TDUserCollect getUserCollect(String openId);
}

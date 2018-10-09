package com.wenyansoft.sugarbean.ibatis.dao;

import com.wenyansoft.sugarbean.ibatis.entity.UserInfo;

public interface UserInfoDao {
	
	public void addUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(String openid);
	
	public void update(UserInfo userInfo);
}

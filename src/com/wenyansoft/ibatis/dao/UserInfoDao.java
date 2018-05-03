package com.wenyansoft.ibatis.dao;

import com.wenyansoft.ibatis.entry.UserInfo;

public interface UserInfoDao {
	
	public void addUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(String openid);
	
	public void update(UserInfo userInfo);
}

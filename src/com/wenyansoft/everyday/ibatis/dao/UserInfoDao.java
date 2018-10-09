package com.wenyansoft.everyday.ibatis.dao;

import com.wenyansoft.everyday.ibatis.entry.UserInfo;

public interface UserInfoDao {
	
	public void addUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(String openid);
	
	public void update(UserInfo userInfo);
}

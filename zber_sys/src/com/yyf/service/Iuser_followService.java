package com.yyf.service;

import java.util.List;

import com.yyf.inter.Iuser_follow;
import com.yyf.model.Tab_user_follow;

public interface Iuser_followService extends Iuser_follow {

	@Override
	int getWhereOpenidCount(String openid);
	
	@Override
	Tab_user_follow getWhereUserID(String user_id, String openid);
	
	@Override
	int getWhereUserIdCount(String user_id);
	
	@Override
	int save(Tab_user_follow tab);
	
	@Override
	int update(Tab_user_follow tab);
		
	@Override
	List<Tab_user_follow> getWhereOpenid(String openid);
	
}

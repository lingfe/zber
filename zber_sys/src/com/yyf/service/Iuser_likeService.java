package com.yyf.service;

import com.yyf.inter.Iuser_like;
import com.yyf.model.Tab_user_like;

public interface Iuser_likeService extends Iuser_like  {

	
	@Override
	int getProjectLikeNum(String project_id);
	
	@Override
	Tab_user_like getWhereOpenid(String project_id, String openid);
	
	@Override
	int save(Tab_user_like tab_project_like_num);
	
	@Override
	int setUpdateState(String project_id, String openid, int state);

	int getWhereOpenidCount(String openid);
	
}

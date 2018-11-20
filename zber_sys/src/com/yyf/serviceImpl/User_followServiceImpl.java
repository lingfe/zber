package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Iuser_followMapper;
import com.yyf.model.Tab_user_follow;
import com.yyf.service.Iuser_followService;

@Service
public class User_followServiceImpl implements Iuser_followService {

	@Autowired
	private Iuser_followMapper iuser_followMapper;
	
	
	@Override
	public int getWhereOpenidCount(String openid) {
		return iuser_followMapper.getWhereOpenidCount(openid);
	}

	@Override
	public Tab_user_follow getWhereUserID(String user_id, String openid) {
		return iuser_followMapper.getWhereUserID(user_id, openid);
	}

	@Override
	public int getWhereUserIdCount(String user_id) {
		return iuser_followMapper.getWhereUserIdCount(user_id);
	}

	@Override
	public int save(Tab_user_follow tab) {
		return iuser_followMapper.save(tab);
	}

	@Override
	public int update(Tab_user_follow tab) {
		return iuser_followMapper.update(tab);
	}
	
	@Override
	public List<Tab_user_follow> getWhereOpenid(String openid) {
		return iuser_followMapper.getWhereOpenid(openid);
	}
}

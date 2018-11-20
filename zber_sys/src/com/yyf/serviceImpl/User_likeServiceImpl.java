package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Iuser_likeMapper;
import com.yyf.model.Tab_user_like;
import com.yyf.service.Iuser_likeService;

/**
 * 
  * 文件名：Project_like_numServiceImpl.java
  * 描述： 项目喜欢人数表，业务逻辑层
  * 修改人： lingfe
  * 修改时间：2018年10月4日 上午8:44:23
  * 修改内容：
 */
@Service
public class User_likeServiceImpl implements Iuser_likeService {
	
	@Autowired
	private Iuser_likeMapper iproject_like_numMapper;

	@Override
	public int getProjectLikeNum(String project_id) {
		return iproject_like_numMapper.getProjectLikeNum(project_id);
	}

	@Override
	public Tab_user_like getWhereOpenid(String project_id, String openid) {
		return iproject_like_numMapper.getWhereOpenid(project_id, openid);
	}

	@Override
	public int save(Tab_user_like tab_project_like_num) {
		return iproject_like_numMapper.save(tab_project_like_num);
	}

	@Override
	public int setUpdateState(String project_id, String openid, int state) {
		return iproject_like_numMapper.setUpdateState(project_id, openid, state);
	}

	@Override
	public int getWhereOpenidCount(String openid) {
		return iproject_like_numMapper.getWhereOpenidCount(openid);
	}

}

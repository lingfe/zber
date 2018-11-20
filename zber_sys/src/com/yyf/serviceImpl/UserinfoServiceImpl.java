package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.IuserinfoMapper;
import com.yyf.model.Tab_user_info;
import com.yyf.service.IuserinfoService;

/**
 * 
  * 文件名：UserinfoServiceImpl.java
  * 描述： 用户信息操作接口,service层
  * 修改人： lingfe
  * 修改时间：2018年10月1日 上午8:50:41
  * 修改内容：
 */
@Service
public class UserinfoServiceImpl implements IuserinfoService {

	//自动装配
	@Autowired
	private IuserinfoMapper iuserinfoMapper;
	
	@Override
	public int save(Tab_user_info tab_user_info) {
		return iuserinfoMapper.save(tab_user_info);
	}

	@Override
	public Tab_user_info getWhereOpenid(String openid) {
		return iuserinfoMapper.getWhereOpenid(openid);
	}

	@Override
	public int update_relo(int relo, String openid) {
		return iuserinfoMapper.update_relo(relo, openid);
	}

	@Override
	public Tab_user_info getWhereUserID(String userid) {
		return iuserinfoMapper.getWhereUserID(userid);
	}

	@Override
	public Integer update_info(java.util.Map<String, Object> map) {
		return iuserinfoMapper.update_info(map);
	}

	
}

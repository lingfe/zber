package com.yyf.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.mapper.Iuser_apply_merchantMapper;
import com.yyf.model.Tab_user_apply_merchant;
import com.yyf.service.Iuser_apply_merchantService;
import com.yyf.service.IuserinfoService;

@Service
public class User_apply_merchantServiceImpl implements Iuser_apply_merchantService {

	@Autowired
	private Iuser_apply_merchantMapper iuser_apply_merchantMapper;
	
	//自动装配
	@Autowired
	private IuserinfoService iuserinfoService;
		
	
	@Override
	@Transactional
	public int updateState(int state, String version, String id,String openid) {
		//0=普通用户 1=商户用户 2=管理用户3 =企业用户
		if(state == 1){
			Tab_user_apply_merchant tab=iuser_apply_merchantMapper.getWhereId(id);
			//修改用户的角色为商户，
			Map<String, Object>  map =new HashMap<>();
			map.put("relo", state);
			map.put("openid", tab.getCreator());
			map.put("is_merchant", 1);
			//确定该用户为商户
			iuserinfoService.update_info(map);
		}
		int tt=iuser_apply_merchantMapper.updateState(state, version, id, openid);
		return tt;
	}

	@Override
	public List<Tab_user_apply_merchant> getWhereState(int state) {
		return iuser_apply_merchantMapper.getWhereState(state);
	}

	@Override
	public Tab_user_apply_merchant getWhereOpenid(String openid) {
		return iuser_apply_merchantMapper.getWhereOpenid(openid);
	}

	@Override
	public int updateTab_user_apply_merchant(String id, Tab_user_apply_merchant tab_user_apply_merchant) {
		return iuser_apply_merchantMapper.updateTab_user_apply_merchant(id, tab_user_apply_merchant);
	}

	@Override
	public int save(Tab_user_apply_merchant tab_user_apply_merchant) {
		return iuser_apply_merchantMapper.save(tab_user_apply_merchant);
	}

	@Override
	public Tab_user_apply_merchant getWhereId(String id) {
		return iuser_apply_merchantMapper.getWhereId(id);
	}

}

package com.yyf.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Iapply_shopsMapper;
import com.yyf.model.Tab_apply_shops;
import com.yyf.service.Iapply_shopsService;

@Service
public class Apply_shopsServiceImpl implements Iapply_shopsService {

	@Autowired
	private Iapply_shopsMapper iapply_shopsMapper;
	
	
	@Override
	public Tab_apply_shops getWhereId(String id) {
		return iapply_shopsMapper.getWhereId(id);
	}
	
	@Override
	public List<Tab_apply_shops> getWhereOpenid(String openid) {
		return iapply_shopsMapper.getWhereOpenid(openid);
	}

	@Override
	public int save(Tab_apply_shops tab_apply_shops) {
		return iapply_shopsMapper.save(tab_apply_shops);
	}

	@Override
	public int updateWhereId(Tab_apply_shops tab) {
		return iapply_shopsMapper.updateWhereId(tab);
	}

	@Override
	public List<Tab_apply_shops> getWhere_type_menu_id(String type_menu_id) {
		return iapply_shopsMapper.getWhere_type_menu_id(type_menu_id);
	}

	@Override
	public List<Tab_apply_shops> getRecommend(Map<String, Object> map) {
		return iapply_shopsMapper.getRecommend(map);
	}

}

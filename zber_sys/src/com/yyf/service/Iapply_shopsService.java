package com.yyf.service;

import java.util.List;
import java.util.Map;

import com.yyf.inter.Iapply_shops;
import com.yyf.inter.Irecommend;
import com.yyf.model.Tab_apply_shops;


public interface Iapply_shopsService extends Iapply_shops,Irecommend<Tab_apply_shops> {
	
	@Override
	Tab_apply_shops getWhereId(String openid);
	
	@Override
	List<Tab_apply_shops> getWhereOpenid(String openid);
	
	@Override
	int save(Tab_apply_shops tab_apply_shops);
	
	@Override
	int updateWhereId(Tab_apply_shops tab);
	
	@Override
	List<Tab_apply_shops> getWhere_type_menu_id(String type_menu_id);
	
	@Override
	List<Tab_apply_shops> getRecommend(Map<String, Object> map);
	
}

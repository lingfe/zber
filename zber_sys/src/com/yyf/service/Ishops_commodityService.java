package com.yyf.service;

import java.util.List;

import com.yyf.inter.Ishops_commodity;
import com.yyf.model.Tab_shops_commodity;

public interface Ishops_commodityService extends Ishops_commodity {

	@Override
	List<Tab_shops_commodity> getWhereShopsChooseType_tabs_id(String shopsChooseType_tabs_id);
	
	@Override
	int save(Tab_shops_commodity tab);
	
	@Override
	List<Tab_shops_commodity> getWhere_shops_recommend(String shops_recommend);
	
	@Override
	Tab_shops_commodity getWhereID(String id);
	
	@Override
	int update(Tab_shops_commodity tab);
	
	@Override
	int deleteWhereId(String id);
}

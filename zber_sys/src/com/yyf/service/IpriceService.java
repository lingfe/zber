package com.yyf.service;

import com.yyf.inter.Iprice;
import com.yyf.model.Tab_price;

public interface IpriceService extends Iprice {

	Tab_price getWhwereSetId(String setid);
	
	@Override
	int save(Tab_price tab_price);
	
	@Override
	int update(Tab_price tab);
	
	@Override
	Tab_price getWhereID(String id);
	
	@Override
	int deleteWhereSetId(String setId);
}

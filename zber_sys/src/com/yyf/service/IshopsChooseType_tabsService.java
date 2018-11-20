package com.yyf.service;

import java.util.List;

import com.yyf.inter.IshopsChooseType_tabs;
import com.yyf.model.Tab_shopsChooseType_tabs;

public interface IshopsChooseType_tabsService extends IshopsChooseType_tabs {
	
	@Override
	Tab_shopsChooseType_tabs getWhereId(String id);

	@Override
	List<Tab_shopsChooseType_tabs> getWhereShopsId(String shops_id);
	
	@Override
	int save(Tab_shopsChooseType_tabs tab);
	
	@Override
	int deleteWhereId(String id);
	
	@Override
	int updateWhereId(Tab_shopsChooseType_tabs tab);
}

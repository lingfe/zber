package com.yyf.service;

import java.util.List;

import com.yyf.inter.Itabs_content;
import com.yyf.model.Tab_tabs_content;

public interface Itabs_contentService extends Itabs_content {
	
	@Override
	Tab_tabs_content getWhereId(String id);
	
	@Override
	int update(Tab_tabs_content tab);
	
	@Override
	int deleteWhereGetId(String get_id);
	
	@Override
	int deleteWhereId(String id);

	@Override
	List<Tab_tabs_content> getWhereGetID(String get_id);
	
	@Override
	int save(Tab_tabs_content tab);
	
}

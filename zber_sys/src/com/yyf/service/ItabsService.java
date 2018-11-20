package com.yyf.service;

import java.util.List;

import com.yyf.model.Tab_tabs;

public interface ItabsService {
	
	Tab_tabs getWhereId(String id);
	
	int deleteWhereId(String id);
	
	int updateWhereId(Tab_tabs tab);

	List<Tab_tabs> getWhere_project_id(String project_id);
	
	int save(Tab_tabs tab);
}

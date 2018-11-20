package com.yyf.service;

import com.yyf.inter.Iuser_browse;
import com.yyf.model.Tab_user_browse;

public interface Iuser_browseService extends Iuser_browse {

	@Override
	int getConut(String project_id);
	
	@Override
	int save(Tab_user_browse tab_user_browse);
}

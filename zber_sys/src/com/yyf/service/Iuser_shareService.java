package com.yyf.service;

import com.yyf.inter.Iuser_share;
import com.yyf.model.Tab_user_share;

public interface Iuser_shareService extends Iuser_share {

	@Override
	int getCount(String project_id);
	
	@Override
	int save(Tab_user_share tab_user_shear);
	
}

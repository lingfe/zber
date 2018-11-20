package com.yyf.service;

import com.yyf.inter.Iuser_apply_merchant;
import com.yyf.model.Tab_user_apply_merchant;

public interface Iuser_apply_merchantService extends Iuser_apply_merchant {

	@Override
	int save(Tab_user_apply_merchant tab_user_apply_merchant);
	
	@Override
	Tab_user_apply_merchant getWhereOpenid(String openid);
	
	@Override
	int updateState(int state, String version, String id, String openid);
}

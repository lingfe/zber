package com.yyf.service;

import java.util.List;

import com.yyf.model.Tab_type_menu;

public interface Itype_menuService {

	List<Tab_type_menu> getWhereSuperiorId(	String superiorId);
}

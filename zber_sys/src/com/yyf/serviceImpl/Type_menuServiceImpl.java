package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Itype_menuMapper;
import com.yyf.model.Tab_type_menu;
import com.yyf.service.Itype_menuService;

@Service
public class Type_menuServiceImpl implements Itype_menuService {
	
	@Autowired
	private Itype_menuMapper itype_menuMapper;

	@Override
	public List<Tab_type_menu> getWhereSuperiorId(String superiorId) {
		return itype_menuMapper.getWhereSuperiorId(superiorId);
	}

}

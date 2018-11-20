package com.yyf.service;

import java.util.List;

import com.yyf.inter.Iimages;
import com.yyf.model.Tab_images;

public interface IimagesService extends Iimages {
	
	@Override
	int deleteWhereId(String id);
 
	@Override
	List<Tab_images> getWhereLbtAttributeId(String setId);
	
	@Override
	int save(Tab_images img);
}

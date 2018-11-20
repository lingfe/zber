package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Ilbt_attributeMapper;
import com.yyf.model.Tab_lbt_attribute;
import com.yyf.service.Ilbt_attributeService;

@Service
public class Lbt_attributeServiceImpl implements Ilbt_attributeService {
	
	@Autowired
	private Ilbt_attributeMapper ilbt_attributeService;

	@Override
	public Tab_lbt_attribute getLbtAttributeInfo(String setId) {
		return ilbt_attributeService.getLbtAttributeInfo(setId);
	}
}

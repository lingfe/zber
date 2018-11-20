package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.IimagesMapper;
import com.yyf.model.Tab_images;
import com.yyf.service.IimagesService;

@Service
public class IimagesServiceImpl implements IimagesService {

	@Autowired
	private IimagesMapper iimagesMapper;

	@Override
	public List<Tab_images> getWhereLbtAttributeId(String setId) {
		return iimagesMapper.getWhereLbtAttributeId(setId);
	}

	@Override
	public int save(Tab_images img) {
		return iimagesMapper.save(img);
	}

	@Override
	public int deleteWhereId(String id) {
		return iimagesMapper.deleteWhereId(id);
	}
	
	
}

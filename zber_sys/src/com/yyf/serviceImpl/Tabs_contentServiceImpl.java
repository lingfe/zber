package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.mapper.IimagesMapper;
import com.yyf.mapper.Itabs_contentMapper;
import com.yyf.model.Tab_tabs_content;
import com.yyf.service.Itabs_contentService;

@Service
public class Tabs_contentServiceImpl implements Itabs_contentService {

	@Autowired
	private Itabs_contentMapper itabs_contentMapper;
	
	@Autowired 
	private	IimagesMapper iimagesMapper;
	
	@Override
	public Tab_tabs_content getWhereId(String id) {
		return itabs_contentMapper.getWhereId(id);
	}
	
	@Override
	public int update(Tab_tabs_content tab) {
		return itabs_contentMapper.update(tab);
	}
	
	@Override
	public int deleteWhereGetId(String get_id) {
		return itabs_contentMapper.deleteWhereGetId(get_id);
	}
	
	@Override
	@Transactional
	public int deleteWhereId(String id) {
		//根据tabs导航菜单内容id删除
		iimagesMapper.getWhereLbtAttributeId(id);
		return itabs_contentMapper.deleteWhereId(id);
	}
	
	@Override
	public List<Tab_tabs_content> getWhereGetID(String get_id) {
		return itabs_contentMapper.getWhereGetID(get_id);
	}

	@Override
	public int save(Tab_tabs_content tab) {
		return itabs_contentMapper.save(tab);
	}

}

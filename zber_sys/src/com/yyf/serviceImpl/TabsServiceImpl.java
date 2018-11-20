package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.ItabsMapper;
import com.yyf.model.Tab_tabs;
import com.yyf.service.ItabsService;

@Service
public class TabsServiceImpl implements ItabsService {
	
	@Autowired
	private ItabsMapper itabsMapper;

	@Override
	public List<Tab_tabs> getWhere_project_id(String project_id) {
		return itabsMapper.getWhere_project_id(project_id);
	}

	@Override
	public int deleteWhereId(String id) {
		return itabsMapper.deleteWhereId(id);
	}

	@Override
	public int updateWhereId(Tab_tabs tab) {
		return itabsMapper.updateWhereId(tab);
	}

	@Override
	public Tab_tabs getWhereId(String id) {
		return itabsMapper.getWhereId(id);
	}

	@Override
	public int save(Tab_tabs tab) {
		return itabsMapper.save(tab);
	}

}

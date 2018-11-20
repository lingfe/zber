package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.IshopsChooseType_tabsMapper;
import com.yyf.model.Tab_shopsChooseType_tabs;
import com.yyf.service.IshopsChooseType_tabsService;

@Service
public class ShopsChooseType_tabsServiceImpl implements IshopsChooseType_tabsService {
	
	@Autowired
	private IshopsChooseType_tabsMapper ishopsChooseType_tabsMapper;

	@Override
	public List<Tab_shopsChooseType_tabs> getWhereShopsId(String shops_id) {
		return ishopsChooseType_tabsMapper.getWhereShopsId(shops_id);
	}

	@Override
	public int save(Tab_shopsChooseType_tabs tab) {
		return ishopsChooseType_tabsMapper.save(tab);
	}

	@Override
	public int deleteWhereId(String id) {
		return ishopsChooseType_tabsMapper.deleteWhereId(id);
	}

	@Override
	public int updateWhereId(Tab_shopsChooseType_tabs tab) {
		return ishopsChooseType_tabsMapper.updateWhereId(tab);
	}

	@Override
	public Tab_shopsChooseType_tabs getWhereId(String id) {
		return ishopsChooseType_tabsMapper.getWhereId(id);
	}

}

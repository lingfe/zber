package com.yyf.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.mapper.IpriceMapper;
import com.yyf.mapper.Ishops_commodityMapper;
import com.yyf.model.Tab_shops_commodity;
import com.yyf.service.Ishops_commodityService;

@Service
public class Shops_commodityServiceImpl implements Ishops_commodityService {
	
	@Autowired
	private Ishops_commodityMapper ishops_commodityMapper;
	
	@Autowired
	private IpriceMapper ipriceMapper;

	@Override
	public List<Tab_shops_commodity> getWhereShopsChooseType_tabs_id(String shopsChooseType_tabs_id) {
		return ishops_commodityMapper.getWhereShopsChooseType_tabs_id(shopsChooseType_tabs_id);
	}

	@Override
	public int save(Tab_shops_commodity tab) {
		return ishops_commodityMapper.save(tab);
	}

	@Override
	public List<Tab_shops_commodity> getWhere_shops_recommend(String shops_recommend) {
		return ishops_commodityMapper.getWhere_shops_recommend(shops_recommend);
	}

	@Override
	public Tab_shops_commodity getWhereID(String id) {
		return ishops_commodityMapper.getWhereID(id);
	}

	@Override
	public int update(Tab_shops_commodity tab) {
		return ishops_commodityMapper.update(tab);
	}

	@Override
	@Transactional
	public int deleteWhereId(String id) {
		//同时删除价格信息
		ipriceMapper.deleteWhereSetId(id);
		return ishops_commodityMapper.deleteWhereId(id);
	}

}

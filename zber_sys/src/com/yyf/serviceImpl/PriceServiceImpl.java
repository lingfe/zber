package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.IpriceMapper;
import com.yyf.model.Tab_price;
import com.yyf.service.IpriceService;

@Service
public class PriceServiceImpl implements IpriceService {
	
	@Autowired
	private IpriceMapper ipriceMapper;

	@Override
	public Tab_price getWhwereSetId(String setid) {
		return ipriceMapper.getWhwereSetId(setid);
	}

	@Override
	public int save(Tab_price tab_price) {
		return ipriceMapper.save(tab_price);
	}

	@Override
	public int update(Tab_price tab) {
		return ipriceMapper.update(tab);
	}

	@Override
	public Tab_price getWhereID(String id) {
		return ipriceMapper.getWhereID(id);
	}

	@Override
	public int deleteWhereSetId(String setId) {
		return ipriceMapper.deleteWhereSetId(setId);
	}

}

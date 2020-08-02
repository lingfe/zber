package com.yyf.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Irelease_infoMapper;
import com.yyf.model.Tab_release_info;
import com.yyf.service.Irelease_infoService;

@Service
public class Release_infoServiceImpl implements Irelease_infoService {
	
	@Autowired
	private Irelease_infoMapper irelease_infoMapper;

	@Override
	public List<Tab_release_info> getWhereOpenId(String openid) {
		return irelease_infoMapper.getWhereOpenId(openid);
	}

	@Override
	public List<Tab_release_info> getPageAll() {
		return irelease_infoMapper.getPageAll();
	}

	@Override
	public Tab_release_info getWhereId(String id) {
		return irelease_infoMapper.getWhereId(id);
	}

	@Override
	public List<Tab_release_info> getWhere_TypeMenu_id(String typeMenu_id) {
		return irelease_infoMapper.getWhere_TypeMenu_id(typeMenu_id);
	}

	@Override
	public List<Tab_release_info> getRecommend(Map<String, Object> map) {
		return irelease_infoMapper.getRecommend(map);
	}

	@Override
	public Tab_release_info getWhereOpenId_Top1(String openid) {
		return irelease_infoMapper.getWhereOpenId_Top1(openid);
	}

	@Override
	public int save(Tab_release_info tab) {
		return irelease_infoMapper.save(tab);
	}

	@Override
	public int update(Tab_release_info tab) {
		return irelease_infoMapper.update(tab);
	}

	@Override
	public int deleteWhereId(String id) {
		return irelease_infoMapper.deleteWhereId(id);
	}

	@Override
	public int updateWhereId_state(String id, int state) {
		return irelease_infoMapper.updateWhereId_state(id, state);
	}

}

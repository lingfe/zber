package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Iuser_browseMapper;
import com.yyf.model.Tab_user_browse;
import com.yyf.service.Iuser_browseService;

/**
 * 
  * 文件名：User_browseServiceImpl.java
  * 描述：浏览表业务逻辑层。 
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午3:42:05
  * 修改内容：
 */
@Service
public class User_browseServiceImpl implements Iuser_browseService {
	
	@Autowired 
	private Iuser_browseMapper iuser_browseMapper;

	@Override
	public int getConut(String project_id) {
		return iuser_browseMapper.getConut(project_id);
	}

	@Override
	public int save(Tab_user_browse tab_user_browse) {
		return iuser_browseMapper.save(tab_user_browse);
	}

}

package com.yyf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.mapper.Iuser_shareMapper;
import com.yyf.model.Tab_user_share;
import com.yyf.service.Iuser_shareService;

/**
 * 
  * 文件名：User_shareServiceImpl.java
  * 描述： 分享表业务逻辑层。
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午3:41:31
  * 修改内容：
 */
@Service
public class User_shareServiceImpl implements Iuser_shareService {
	
	@Autowired
	private Iuser_shareMapper iuser_shareMapper;

	@Override
	public int getCount(String project_id) {
		return iuser_shareMapper.getCount(project_id);
	}

	@Override
	public int save(Tab_user_share tab_user_shear) {
		return iuser_shareMapper.save(tab_user_shear);
	}

}

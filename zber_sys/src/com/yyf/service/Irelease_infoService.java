package com.yyf.service;

import java.util.List;
import java.util.Map;

import com.yyf.inter.Irecommend;
import com.yyf.model.Tab_release_info;

public interface Irelease_infoService extends Irecommend<Tab_release_info> {
	
	int update(Tab_release_info tab);
	
	int save(Tab_release_info tab);
	
	/**
	 * 
	 * 根据openid得到该用户最新发布的一条项目
	 * @author lingfe     
	 * @created 2018年10月21日 上午10:52:45  
	 * @param map
	 * @return
	 */
	Tab_release_info getWhereOpenId_Top1(String openid);
	
	/**
	 * 
	 * 分页得到推荐的项目信息
	 * @author lingfe     
	 * @created 2018年10月21日 上午10:36:06  
	 * @param map
	 * @return
	 */
	@Override
	List<Tab_release_info> getRecommend(Map<String, Object> map);
	
	/**
	 * 
	 * 根据openid得到，该用户发布的项目
	 * @author lingfe     
	 * @created 2018年10月21日 上午10:34:26  
	 * @param openid
	 * @return
	 */
	List<Tab_release_info> getWhereOpenId(String openid);
	
	/**
	 * 
	 * 分页得到发布的项目数据
	 * @author lingfe
	 * @created 2018年10月2日 下午9:11:19  
	 * @return
	 */
	List<Tab_release_info> getPageAll();
	
	/**
	 * 
	 * 根据项目id得到项目信息
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:26:20  
	 * @param id
	 * @return
	 */
	Tab_release_info getWhereId(String id);
	
	/**
	 * 
	 * 根据分类菜单id得到项目信息
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:57:21  
	 * @param typeMenu_id
	 * @return
	 */
	List<Tab_release_info> getWhere_TypeMenu_id(String typeMenu_id);
	
}

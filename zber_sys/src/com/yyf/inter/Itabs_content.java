package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_tabs_content;

/**
 * 
  * 文件名：Itabs_content.java
  * 描述： 用于tabs导航菜单内容表数据层，业务逻辑层访问操作
  * 修改人： lingfe
  * 修改时间：2018年11月17日 下午4:08:59
  * 修改内容：
 */
public interface Itabs_content {
	
	/**
	 * 
	 * 根据id标识执行查询
	 * @author lingfe     
	 * @created 2018年11月19日 下午12:05:21  
	 * @param id
	 * @return
	 */
	Tab_tabs_content getWhereId(String id);
	
	/**
	 * 
	 * 根据id标识修改内容
	 * @author lingfe     
	 * @created 2018年11月19日 上午11:54:33  
	 * @param id
	 * @return
	 */
	int update(Tab_tabs_content tab);
	
	/**
	 * 根据get_id删除tabs导航菜单内容。
	 * @author lingfe     
	 * @created 2018年11月19日 上午10:22:42  
	 * @param get_id
	 * @return
	 */
	int deleteWhereGetId(String get_id);
	
	/**
	 * 根据id删除tabs菜单导航内容
	 * @author lingfe     
	 * @created 2018年11月19日 上午10:21:17  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);

	/**
	 * 
	 * 保存
	 * @author lingfe     
	 * @created 2018年11月17日 下午4:10:20  
	 * @param tab
	 * @return
	 */
	int save(Tab_tabs_content tab);
	
	/**
	 * 
	 * 根据getId查询数据
	 * @author lingfe     
	 * @created 2018年11月17日 下午4:11:17  
	 * @param get_id
	 * @return
	 */
	List<Tab_tabs_content> getWhereGetID(String get_id);
}

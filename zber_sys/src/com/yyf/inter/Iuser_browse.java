package com.yyf.inter;

import com.yyf.model.Tab_user_browse;

/**
 * 
  * 文件名：Iuser_browse.java
  * 描述： 全局的接口。用于用户浏览表数据访问操作
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午3:19:13
  * 修改内容：
 */
public interface Iuser_browse {
	
	/**
	 * 
	 * 保存、记录一次项目浏览。
	 * @author lingfe     
	 * @created 2018年10月4日 下午3:20:27  
	 * @param tab_user_browse
	 * @return
	 */
	int save(Tab_user_browse tab_user_browse);
	
	/**
	 * 
	 * 根据项目id得到该项目的浏览次数
	 * @author lingfe     
	 * @created 2018年10月4日 下午3:21:24  
	 * @param project_id
	 * @return
	 */
	int getConut(String project_id);
}

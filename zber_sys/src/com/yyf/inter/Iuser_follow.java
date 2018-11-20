package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_user_follow;

/**
 * 
  * 文件名：Iuser_follow.java
  * 描述：用户关注表数据访问接口，用于数据访问层，业务逻辑层 
  * 修改人： lingfe
  * 修改时间：2018年10月19日 下午12:37:59
  * 修改内容：
 */
public interface Iuser_follow {
	
	/**
	 * 
	 * 根据关注者，得到关注数据
	 * @author lingfe     
	 * @created 2018年10月20日 下午3:02:59  
	 * @param openid
	 * @return
	 */
	List<Tab_user_follow> getWhereOpenid(String openid);
	
	/**
	 * 
	 * 根据user_id(被关注者)得到被关注人数
	 * @author lingfe     
	 * @created 2018年10月19日 下午12:57:21  
	 * @param user_id
	 * @return
	 */
	int getWhereUserIdCount(String user_id);
	
	/**
	 * 
	 * 根据openid(表示关注者)，查询得到关注人数
	 * @author lingfe     
	 * @created 2018年10月19日 下午12:47:31  
	 * @param openid
	 * @return
	 */
	int getWhereOpenidCount(String openid);
	
	/**
	 * 
	 * 根据tab.id修改关注数据
	 * @author lingfe     
	 * @created 2018年10月19日 下午12:43:48  
	 * @param tab
	 * @return
	 */
	int update(Tab_user_follow tab);
	
	/**
	 * 
	 * 根据用户id(被关注者),以及openid(关注者)查询关注数据
	 * @author lingfe     
	 * @created 2018年10月19日 下午12:42:46  
	 * @param user_id
	 * @return
	 */
	Tab_user_follow getWhereUserID(String user_id,String openid);

	/**
	 * 
	 * 保存一条关注数据
	 * @author lingfe     
	 * @created 2018年10月19日 下午12:38:59  
	 * @param tab
	 * @return
	 */
	int save(Tab_user_follow tab);
	
}

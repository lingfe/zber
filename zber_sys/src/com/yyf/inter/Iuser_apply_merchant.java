package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_user_apply_merchant;

/**
 * 
  * 文件名：Iuser_apply_merchant.java
  * 描述： 用户申请成为商户表,接口。用于数据访问层，业务逻辑层
  * 修改人： lingfe
  * 修改时间：2018年10月6日 下午4:15:24
  * 修改内容：
 */
public interface Iuser_apply_merchant {

	/**
	 * 
	 * 申请成为商户，保存用户申请信息
	 * @author lingfe     
	 * @created 2018年10月6日 下午4:19:19  
	 * @param tab_user_apply_merchant
	 * @return
	 */
	int save(Tab_user_apply_merchant tab_user_apply_merchant);
	
	/**
	 * 
	 * 修改状态。同时修改数据版本以及修改者
	 * @author  lingfe   
	 * @created 2018年10月6日 下午4:21:54  
	 * @param state 状态
	 * @param version	版本
	 * @param id 
	 * @param openid 修改人
	 * @return
	 */
	int updateState(int state,String version,String id,String openid);
	
	/**
	 * 
	 * 根据状态获取用户申请商户信息集合
	 * @author lingfe
	 * @created 2018年10月6日 下午4:25:12  
	 * @param state
	 * @return
	 */
	List<Tab_user_apply_merchant> getWhereState(int state);
	
	/**
	 * 
	 * 根据openid得到获取用户申请商户信息
	 * @author lingfe     
	 * @created 2018年10月6日 下午4:27:57  
	 * @param openid 微信小程序用户
	 * @return
	 */
	Tab_user_apply_merchant getWhereOpenid(String openid);
	
	/**
	 * 
	 * 根据id修改,用户申请商户信息
	 * @author lingfe     
	 * @created 2018年10月6日 下午4:29:27  
	 * @param id
	 * @return
	 */
	int updateTab_user_apply_merchant(String id,Tab_user_apply_merchant tab_user_apply_merchant);
	
	/**
	 * 根据id查询
	 * @author lingfe     
	 * @created 2018年10月6日 下午10:13:02  
	 * @param id
	 * @return
	 */
	Tab_user_apply_merchant getWhereId(String id);
}

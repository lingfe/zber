package com.yyf.service;


import java.util.Map;

import com.yyf.model.Tab_user_info;

public interface IuserinfoService {
	
	/**
	 * 
	 * 修改其他字段
	 * @author lingfe     
	 * @created 2018年10月23日 下午6:01:48  
	 * @param map
	 * @return
	 */
	Integer update_info(Map<String, Object> map);
	
	/**
	 * 
	 * 保存用户
	 * @author lingfe     
	 * @created 2017年12月18日 下午2:03:29  
	 * @param 实体
	 * @return
	 */
   int save(Tab_user_info tab_user_info);
   
   /**
    * 
    * 根据openid查询用户
    * @author lingfe     
    * @created 2018年10月1日 下午11:49:13  
    * @param openid
    * @return 数据
    */
   Tab_user_info getWhereOpenid(String openid);
   
   /**
    * 
    * 根据openid修改用户角色
    * @author lingfe     
    * @created 2018年10月6日 下午10:29:13  
    * @param relo
    * @param openid
    * @return
    */
   int update_relo(int relo,String openid);
   
   /**
    * 
    * 根据用户id查询用户
    * @author lingfe     
    * @created 2018年10月21日 上午10:43:06  
    * @param userid
    * @return
    */
   Tab_user_info getWhereUserID(String userid);
}	

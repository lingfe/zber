package com.yyf.inter;

import com.yyf.model.Tab_user_like;

/**
 * 
  * 文件名：Iproject_like_num.java
  * 描述：提供给，数据访问层，业务层的接口 
  * 修改人： lingfe
  * 修改时间：2018年10月4日 上午8:09:04
  * 修改内容：
 */
public interface Iuser_like {
	
	/**
	 * 
	 * 根据openid(创建者),得到喜欢的总数量
	 * @author lingfe     
	 * @created 2018年10月26日 下午3:39:44  
	 * @param openid
	 * @return
	 */
	int getWhereOpenidCount(String openid);

	/**
	 * 
	 * 根据项目id得到该项目的喜欢人数
	 * @author lingfe     
	 * @created 2018年10月4日 上午8:10:36  
	 * @param project_id
	 * @return
	 */
	int getProjectLikeNum(String project_id);
	
	/**
	 * 
	 * 保存、添加、设置一条喜欢数
	 * @author lingfe
	 * @created 2018年10月4日 上午8:22:57  
	 * @param tab_project_like_num
	 * @return
	 */
	int save(Tab_user_like tab_project_like_num);
	
	/**
	 * 
	 * 根据openid和项目id,得到用户喜欢的该项目
	 * @author lingfe     
	 * @created 2018年10月4日 上午8:17:16  
	 * @param openid
	 * @return
	 */
	Tab_user_like getWhereOpenid(String project_id, String openid);
	
	/**
	 * 
	 * 根据openid、项目id,修改是否喜欢该项目
	 * @author lingfe     
	 * @created 2018年10月4日 上午8:29:20  
	 * @param project_id 项目id
	 * @param openid 用户id
	 * @param state 状态 0=不喜欢,1=喜欢
	 * @return
	 */
	int setUpdateState(String project_id, String openid, int state);
}

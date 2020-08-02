package com.yyf.inter;

/**
 * 
  * 文件名：Irelease_info.java
  * 描述： 个人发布信息表数据访问接口
  * 修改人： lingfe
  * 修改时间：2019年3月25日 下午3:10:37
  * 修改内容：
 */
public interface Irelease_info {
	
	/**
	 * 
	 * 根据项目id修改状态
	 * @author lingfe     
	 * @created 2019年3月31日 下午9:57:46  
	 * @return
	 */
	int updateWhereId_state(String id,int state);

	/**
	 * 
	 * 根据id标识删除发布信息
	 * @author lingfe     
	 * @created 2019年3月25日 下午3:11:26  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
}

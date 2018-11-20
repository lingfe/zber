package com.yyf.inter;

import com.yyf.model.Tab_user_share;

public interface Iuser_share {
	
	/**
	 * 
	 * 保存一条分享
	 * @author lingfe     
	 * @created 2018年10月4日 下午2:41:11  
	 * @param tab_user_shear
	 * @return
	 */
	int  save(Tab_user_share tab_user_shear);
	
	/**
	 * 
	 * 根据项目id得到该项目分享次数
	 * @author lingfe
	 * @created 2018年10月4日 下午2:42:35  
	 * @param project_id
	 * @return
	 */
	int getCount(String project_id);
}

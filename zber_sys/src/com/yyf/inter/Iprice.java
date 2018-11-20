package com.yyf.inter;

import com.yyf.model.Tab_price;

public interface Iprice {
	
	/**
	 * 
	 * 根据归属id删除价格参数
	 * @author lingfe     
	 * @created 2018年10月10日 上午11:53:06  
	 * @param setId
	 * @return
	 */
	int deleteWhereSetId(String setId);
	
	/**
	 * 根据id查询价格信息
	 * @author lingfe     
	 * @created 2018年10月9日 下午9:51:01  
	 * @param id
	 * @return
	 */
	Tab_price getWhereID(String id);
	
	/**
	 * 
	 * 根据id修改价格信息
	 * @author lingfe     
	 * @created 2018年10月9日 下午9:44:10  
	 * @param id
	 * @return
	 */
	int update(Tab_price tab);
	
	/**
	 * 
	 * 根据setid也就是归属id得到价格参数
	 * @author lingfe     
	 * @created 2018年10月9日 下午5:33:02  
	 * @param setId
	 * @return
	 */
	Tab_price getWhwereSetId(String setId);

	/**
	 * 
	 * 保存一条价格参数
	 * @author lingfe     
	 * @created 2018年10月9日 下午5:14:52  
	 * @param tab_price
	 * @return
	 */
	int save(Tab_price tab_price);

}

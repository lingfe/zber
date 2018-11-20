package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_shops_commodity;

/**
 * 
  * 文件名：Ishops_commodity.java
  * 描述：商铺商品表接口,用于数据访问层，业务逻辑层 
  * 修改人： lingfe
  * 修改时间：2018年10月7日 下午9:28:56
  * 修改内容：
 */
public interface Ishops_commodity {
	
	/**
	 * 
	 * 根据商品id删除商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 上午11:55:50  
	 * @param id 商品id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 修改商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 上午11:06:53  
	 * @param tab
	 * @return
	 */
	int update(Tab_shops_commodity tab);
	
	/**
	 * 
	 * 根据商品id查询商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 上午10:57:21  
	 * @param id
	 * @return
	 */
	Tab_shops_commodity getWhereID(String id);
	
	/**
	 * 
	 * 根据商铺推荐得到该商铺推荐的所有商品。 top 2
	 * @author lingfe     
	 * @created 2018年10月9日 上午11:26:58  
	 * @param shops_recommend 商铺id
	 * @return
	 */
	List<Tab_shops_commodity> getWhere_shops_recommend(String shops_recommend);

	/**
	 * 
	 * 保存一条商品 数据
	 * @author lingfe     
	 * @created 2018年10月7日 下午8:40:57  
	 * @param tab
	 * @return
	 */
	int save(Tab_shops_commodity tab);
	
	/**
	 * 
	 * 根据shopsChooseType_tabs_id得到商品集合
	 * @author lingfe     
	 * @created 2018年10月7日 下午8:42:44  
	 * @param shops_id
	 * @return
	 */
	List<Tab_shops_commodity> getWhereShopsChooseType_tabs_id(String shopsChooseType_tabs_id);
}

package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_apply_shops;

/**
 * 
  * 文件名：Iuser_apply_merchant.java
  * 描述： 商户的店铺表,接口。用于数据访问层，业务逻辑层
  * 修改人： lingfe
  * 修改时间：2018年10月6日 下午4:15:24
  * 修改内容：
 */
public interface Iapply_shops {
	
	/**
	 * 
	 * 根据id修改编辑
	 * @author lingfe     
	 * @created 2018年10月9日 上午9:47:34  
	 * @param id
	 * @return
	 */
	int updateWhereId(Tab_apply_shops tab);
	
	/**
	 * 
	 * 根据分类菜单id查询，商铺
	 * @author lingfe     
	 * @created 2018年10月9日 上午11:11:02  
	 * @param type_menu_id
	 * @return
	 */
	List<Tab_apply_shops> getWhere_type_menu_id(String type_menu_id);
	
	/**
	 * 
	 * 根据商铺id得到商铺信息
	 * @author lingfe
	 * @created 2018年10月7日 下午5:42:30  
	 * @param openid
	 * @return
	 */
	Tab_apply_shops getWhereId(String id);
	
	/**
	 * 根据openid得到商铺信息 
	 * @author lingfe     
	 * @created 2018年10月7日 下午5:18:06  
	 * @param openid
	 * @return
	 */
	List<Tab_apply_shops> getWhereOpenid(String openid);
	
	/**
	 * 
	 * 保存商铺信息
	 * @author lingfe     
	 * @created 2018年10月7日 下午5:19:21  
	 * @param tab_apply_shops
	 * @return
	 */
	int save(Tab_apply_shops tab_apply_shops);
}

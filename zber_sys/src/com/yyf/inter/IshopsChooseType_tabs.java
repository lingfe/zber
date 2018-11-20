package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_shopsChooseType_tabs;

/**
 * 
  * 文件名：IshopsChooseType_tabs.java
  * 描述： 商铺选购分类tabs表接口。用于数据访问层，业务逻辑层
  * 修改人： lingfe
  * 修改时间：2018年10月7日 下午8:40:00
  * 修改内容：
 */
public interface IshopsChooseType_tabs {
	
	/**
	 * 
	 * 根据id查询商铺选购分类tabs
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:30:35  
	 * @param id
	 * @return
	 */
	Tab_shopsChooseType_tabs getWhereId(String id);
	
	/**
	 * 
	 * 根据id删除商铺选购分类tabs
	 * @author lingfe     
	 * @created 2018年10月8日 上午9:56:41  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 根据id编辑修改商铺选购分类tabs
	 * @author lingfe     
	 * @created 2018年10月8日 上午9:57:09  
	 * @param tab
	 * @return
	 */
	int updateWhereId(Tab_shopsChooseType_tabs tab);

	/**
	 * 
	 * 保存一条商铺选购分类tabs 数据
	 * @author lingfe     
	 * @created 2018年10月7日 下午8:40:57  
	 * @param tab
	 * @return
	 */
	int save(Tab_shopsChooseType_tabs tab);
	
	/**
	 * 
	 * 根据shops_id得到商铺选购分类tabs集合
	 * @author lingfe     
	 * @created 2018年10月7日 下午8:42:44  
	 * @param shops_id
	 * @return
	 */
	List<Tab_shopsChooseType_tabs> getWhereShopsId(String shops_id);
	
}

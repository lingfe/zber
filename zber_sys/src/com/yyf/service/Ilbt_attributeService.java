package com.yyf.service;

import com.yyf.model.Tab_lbt_attribute;

/**
 * 
  * 文件名：Ilbt_attributeService.java
  * 描述： 轮播图属性，业务层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午9:51:16
  * 修改内容：
 */
public interface Ilbt_attributeService {

	/**
	 * 
	 * 根据setId得到轮播图属性
	 * @author lingfe     
	 * @created 2018年10月5日 上午10:16:12  
	 * @param setId 表示分类菜单id，项目id，其他id。
	 * @return
	 */
	Tab_lbt_attribute getLbtAttributeInfo(String setId);
}

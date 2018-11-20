package com.yyf.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yyf.model.Tab_lbt_attribute;

/**
 * 
  * 文件名：Ilbt_attributeMapper.java
  * 描述： 轮播图属性表数据访问接口
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午9:44:17
  * 修改内容：
 */
public interface Ilbt_attributeMapper {

	/**
	 * 
	 * 根据setId得到轮播图属性
	 * @author lingfe     
	 * @created 2018年10月5日 上午10:16:12  
	 * @param setId 表示分类菜单id，项目id，其他id。
	 * @return
	 */
	@Select("SELECT * FROM lbt_attribute WHERE setId=#{setId}")
	Tab_lbt_attribute getLbtAttributeInfo(@Param("setId")String setId);
}

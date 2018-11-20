package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yyf.model.Tab_type_menu;

/**
 * 
  * 文件名：Itype_menu.java
  * 描述： 分类菜单，数据访回接口
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午3:35:19
  * 修改内容：
 */
public interface Itype_menuMapper {

	/**
	 * 
	 * 根据上级id返回分类菜单数据，如果上级id为空，则返回最顶级的数据
	 * @author lingfe     
	 * @created 2018年10月2日 下午3:37:53  
	 * @param superiorId
	 * @return
	 */
	@Select("SELECT * FROM type_menu WHERE superiorId=#{superiorId}")
	List<Tab_type_menu> getWhereSuperiorId(@Param("superiorId") String superiorId);
}

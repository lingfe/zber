package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.model.Tab_tabs;

/**
 * 
  * 文件名：ItabsMapper.java
  * 描述： 项目详情tabs菜单，数据访问
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午8:44:48
  * 修改内容：
 */
public interface ItabsMapper {
	
	@Insert("INSERT  INTO tabs "
			+ "(`id`,`project_id`,`tabs_name`,`content`,`tab_model`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{project_id},#{tabs_name},#{content},#{tab_model},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_tabs tab);
	
	
	@Select("select * from tabs where id=#{id}")
	Tab_tabs getWhereId(@Param("id")String id);
	
	/**
	 * 
	 * 根据id删除tabs
	 * @author lingfe     
	 * @created 2018年10月8日 上午9:56:41  
	 * @param id
	 * @return
	 */
	@Delete("delete FROM tabs where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	/**
	 * 
	 * 根据id编辑tabs
	 * @author lingfe     
	 * @created 2018年10月8日 上午9:57:09  
	 * @param tab
	 * @return
	 */
	@Update("UPDATE tabs SET tabs_name=#{tabs_name},content=#{content},"
			+ "tab_model=#{tab_model},state=#{state},mdate=#{mdate},modify=#{modify},version=#{version}   "
			+ "where id=#{id}")
	int updateWhereId(Tab_tabs tab);
	
	/**
	 * 
	 * 根据项目id得到该项目的项目详情tabs菜单数据集合
	 * @author lingfe
	 * @created 2018年10月2日 下午8:36:35  
	 * @param project_id
	 * @return
	 */
	@Select("SELECT * FROM tabs WHERE project_id=#{project_id}")
	List<Tab_tabs> getWhere_project_id(@Param("project_id") String project_id);
}

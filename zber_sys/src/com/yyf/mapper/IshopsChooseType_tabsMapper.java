package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.IshopsChooseType_tabs;
import com.yyf.model.Tab_shopsChooseType_tabs;

public interface IshopsChooseType_tabsMapper extends IshopsChooseType_tabs {
	
	@Select("select * from shopschoosetype_tabs where id=#{id}")
	Tab_shopsChooseType_tabs getWhereId(@Param("id")String id);
	
	@Delete("delete FROM shopschoosetype_tabs where id=#{id}")
	int deleteWhereId(@Param("id")String id);
	
	@Update("UPDATE shopschoosetype_tabs SET tabs_name=#{tabs_name},content=#{content},"
			+ "model=#{model},state=#{state},mdate=#{mdate},modify=#{modify},version=#{version}   "
			+ "where id=#{id}")
	int updateWhereId(Tab_shopsChooseType_tabs tab);

	@Select("select * from shopschoosetype_tabs where shops_id=#{shops_id}")
	List<Tab_shopsChooseType_tabs> getWhereShopsId(@Param("shops_id")String shops_id);
	
	@Insert("INSERT  INTO shopschoosetype_tabs "
			+ "(`id`,`shops_id`,`tabs_name`,`content`,`model`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{shops_id},#{tabs_name},#{content},#{model},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_shopsChooseType_tabs tab);
}

package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Ishops_commodity;
import com.yyf.model.Tab_shops_commodity;

public interface Ishops_commodityMapper extends Ishops_commodity {
	
	@Delete("delete from shops_commodity where id=#{id}")
	int deleteWhereId(String id);
	
	@Select("select * from shops_commodity where id=#{id}")
	Tab_shops_commodity getWhereID(@Param("id")String id);

	@Select("select * from shops_commodity where shopsChooseType_tabs_id=#{shopsChooseType_tabs_id}")
	List<Tab_shops_commodity> getWhereShopsChooseType_tabs_id(@Param("shopsChooseType_tabs_id")String shopsChooseType_tabs_id);
	
	@Insert("INSERT  INTO shops_commodity "
			+ "(`id`,shops_recommend,`shopsChooseType_tabs_id`,`commodityName`,img,`content`,`model`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{shops_recommend},#{shopsChooseType_tabs_id},#{commodityName},#{img},#{content},#{model},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_shops_commodity tab);
	
	@Update("update shops_commodity set "
			+ "shops_recommend=#{shops_recommend},commodityName=#{commodityName},img=#{img},"
			+ "content=#{content},model=#{model},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_shops_commodity tab);
	
	@Select("select * from shops_commodity where shops_recommend=#{shops_recommend} limit 2 ")
	List<Tab_shops_commodity> getWhere_shops_recommend(@Param("shops_recommend")String shops_recommend);
}

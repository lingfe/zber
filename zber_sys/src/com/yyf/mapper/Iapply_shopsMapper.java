package com.yyf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Iapply_shops;
import com.yyf.inter.Irecommend;
import com.yyf.mapperProvider.PublicMapperProvider;
import com.yyf.model.Tab_apply_shops;

public interface Iapply_shopsMapper extends Iapply_shops ,Irecommend<Tab_apply_shops> {
	
	@SelectProvider(type=PublicMapperProvider.class,method="getRecommend")
	List<Tab_apply_shops> getRecommend(Map<String, Object> map);
	
	@Select("Select * from apply_shops where id=#{id}")
	Tab_apply_shops getWhereId(@Param("id")String id);

	@Select("Select * from apply_shops where creator=#{openid}")
	List<Tab_apply_shops> getWhereOpenid(@Param("openid")String openid);

	@Insert("INSERT  INTO apply_shops "
			+ "(`id`,is_subscribe,type_menu_id,model,`shopsName`,`businessHours`,logo,city,`contactNumber`,`address`,`position_info`,`images`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{is_subscribe},#{type_menu_id},#{model},#{shopsName},#{businessHours},#{logo},#{city},#{contactNumber},#{address},#{position_info},#{images},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_apply_shops tab_apply_shops);
	
	@Update("update apply_shops set shopsName=#{shopsName},"
			+ "type_menu_id=#{type_menu_id},"
			+ "businessHours=#{businessHours},logo=#{logo},contactNumber=#{contactNumber},"
			+ "address=#{address},"
			+ "position_info=#{position_info},images=#{images},state=#{state},mdate=#{mdate},"
			+ "modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int updateWhereId(Tab_apply_shops tab);
	
	@Select("Select * from apply_shops where type_menu_id=#{type_menu_id}")
	List<Tab_apply_shops> getWhere_type_menu_id(@Param("type_menu_id")String type_menu_id);
}

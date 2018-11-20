package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Itabs_content;
import com.yyf.model.Tab_tabs_content;


public interface Itabs_contentMapper extends Itabs_content {
	
	@Select("select * from tabs_content where id=#{id} ")
	Tab_tabs_content getWhereId(@Param("id")String id);
	
	@Update("UPDATE tabs_content SET "
			+ "model=#{model},title=#{title},content_bold_first=#{content_bold_first},"
			+ "content=#{content},content_bold_tail=#{content_bold_tail},content_to=#{content_to},img_txt=#{img_txt},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version}   "
			+ "where id=#{id}")
	int update(Tab_tabs_content tab);
	
	@Delete("delete FROM tabs_content where get_id=#{get_id} ")
	int deleteWhereGetId(@Param("get_id")String get_id);
	
	@Delete("delete From tabs_content where id=#{id} ")
	int deleteWhereId(@Param("id")String id);

	@Select("select * from  tabs_content where get_id=#{get_id} order by cdate ASC ")
	List<Tab_tabs_content> getWhereGetID(@Param("get_id")String get_id);
	
	@Insert("INSERT  INTO tabs_content "
			+ " (`id`,`get_id`,`model`,`title`,"
			+ "`content_bold_first`,`content`,`content_bold_tail`,`content_to`,`img_txt`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ " VALUES "
			+ "(#{id},#{get_id},#{model},#{title},"
			+ "#{content_bold_first},#{content},#{content_bold_tail},#{content_to},#{img_txt},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_tabs_content tab);
}

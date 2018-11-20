package com.yyf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yyf.inter.Iuser_browse;
import com.yyf.model.Tab_user_browse;

/**
 * 
  * 文件名：Iuser_browseMapper.java
  * 描述：用户浏览表数据访问层。 
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午3:22:17
  * 修改内容：
 */
public interface Iuser_browseMapper extends Iuser_browse {
	
	@Select("Select COUNT(*) from user_browse where project_id=#{project_id}")
	int getConut(@Param("project_id")String project_id);
	
	@Insert("INSERT  INTO user_browse "
			+ "(`id`,`project_id`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{project_id},#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_browse tab_user_browse);
}

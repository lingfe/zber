package com.yyf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yyf.inter.Iuser_share;
import com.yyf.model.Tab_user_share;

/**
 * 
  * 文件名：Iuser_shareMapper.java
  * 描述： 分享表数据访问层
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午2:34:37
  * 修改内容：
 */
public interface Iuser_shareMapper extends Iuser_share {

	@Select("Select COUNT(*) from user_share where project_id=#{project_id}")
	int getCount(@Param("project_id")String project_id);
	
	@Insert("INSERT  INTO user_share "
			+ "(`id`,`project_id`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{project_id},#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_share tab_user_shear);
	
}

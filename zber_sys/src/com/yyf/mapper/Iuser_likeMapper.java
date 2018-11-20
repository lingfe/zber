package com.yyf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Iuser_like;
import com.yyf.model.Tab_user_like;

/**
 * 
  * 文件名：Iproject_like_num.java
  * 描述：项目喜欢人数表，数据访问层 
  * 修改人： lingfe
  * 修改时间：2018年10月4日 上午8:09:04
  * 修改内容：
 */
public interface Iuser_likeMapper  extends Iuser_like{

	@Select("SELECT COUNT(*) FROM user_like WHERE project_id=#{project_id}")
	int getProjectLikeNum(@Param("project_id")String project_id);
	
	@Select("SELECT * FROM user_like WHERE project_id=#{project_id} AND creator=#{openid}")
	Tab_user_like getWhereOpenid(@Param("project_id")String project_id, 
			@Param("openid")String openid);

	
	@Insert("INSERT  INTO user_like "
			+ "(`id`,`project_id`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{project_id},#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_like tab_project_like_num);
	
	@Update("UPDATE  user_like SET state=#{state}  WHERE project_id=#{project_id} AND creator=#{openid} ")
	int setUpdateState(@Param("project_id")String project_id, 
			@Param("openid")String openid, 
			@Param("state")int state);
	
	@Select("select count(*) from user_like  where creator=#{openid} ")
	int getWhereOpenidCount(@Param("openid")String openid);
}

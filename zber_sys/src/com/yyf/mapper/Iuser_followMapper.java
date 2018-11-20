package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Iuser_follow;
import com.yyf.model.Tab_user_follow;

public interface Iuser_followMapper extends Iuser_follow {

	@Insert("INSERT  INTO user_follow "
			+ "(`id`,`user_id`,`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ "VALUES "
			+ "(#{id},#{user_id},#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_follow tab);
	
	@Select("Select * from user_follow where user_id=#{user_id} and creator=#{openid}")
	Tab_user_follow getWhereUserID(@Param("user_id")String user_id, 
			@Param("openid")String openid);
	
	@Select("select count(*) from user_follow where creator=#{openid}")
	int getWhereOpenidCount(@Param("openid")String openid);  
	
	@Select("select count(*) from user_follow where user_id=#{user_id}")
	int getWhereUserIdCount(@Param("user_id")String user_id);
	
	@Update("update user_follow set "
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_user_follow tab);
	
	@Select("select * from user_follow where creator=#{openid} and state=1 ")
	List<Tab_user_follow> getWhereOpenid(@Param("openid")String openid);
	
	
}

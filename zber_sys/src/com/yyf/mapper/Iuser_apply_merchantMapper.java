package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Iuser_apply_merchant;
import com.yyf.model.Tab_user_apply_merchant;

public interface Iuser_apply_merchantMapper extends Iuser_apply_merchant {


	@Insert("INSERT  INTO user_apply_merchant "
			+ "(`id`,`sfz`,`realName`,`contactNumber`,`address`,`position_info`,`images`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{sfz},#{realName},#{contactNumber},#{address},#{position_info},#{images},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_apply_merchant tab_user_apply_merchant);
	
	@Select("Select * from user_apply_merchant where creator=#{openid}")
	Tab_user_apply_merchant getWhereOpenid(@Param("openid")String openid);
	
	@Select("select * from user_apply_merchant where state=#{state}")
	List<Tab_user_apply_merchant> getWhereState(@Param("state")int state);
	
	@Update("update user_apply_merchant set state=#{state},version=#{version},modify=#{openid} where id=#{id}")
	int updateState(@Param("state")int state, 
			@Param("version")String version, 
			@Param("id")String id,
			@Param("openid")String openid);
	
	@Select("SELECT * FROM user_apply_merchant WHERE id=#{id}")
	Tab_user_apply_merchant getWhereId(@Param("id")String id);
}

package com.yyf.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.yyf.mapperProvider.IuserinfoMapperProvider;
import com.yyf.model.Tab_user_info;
		
/**
 * 
  * 文件名：Mapper_user_info.java
  * 描述：用户信息数据访问接口 
  * 修改人： lingfe
  * 修改时间：2018年10月1日 上午8:32:31
  * 修改内容：
 */	
public interface IuserinfoMapper {

	//保存用户信息
	@Insert("INSERT  INTO user_info "
			+ "(`id`,is_merchant,openid,`username`,`realname`,`avatar`,`is_follow`,`relo`,`credit`,`is_authentication`,`businessTime`,"
			+ "`pwd`,`tel`,`email`,`mobile`,`qq`,`balance`,`lastTime`,`state`,`idCard`,`provinceCode`,`provinceName`,"
			+ "`cityCode`,`cityName`,`regionCode`,`regionName`,`address`,`remark`,"
			+ "`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ " VALUES  "
			+ "(#{id},#{is_merchant},#{openid},#{username},#{realname},#{avatar},#{is_follow},#{relo},#{credit},#{is_authentication},#{businessTime},"
			+ "#{pwd},#{tel},#{email},#{mobile},#{qq},#{balance},#{lastTime},#{state},#{idCard},#{provinceCode},#{provinceName},"
			+ "#{cityCode},#{cityName},#{regionCode},#{regionName},#{address},#{remark},"
			+ "#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_user_info tab_user_info);
	
	//根据openid查询用户
	@Select("select * FROM user_info where openid=#{openid}")
	Tab_user_info getWhereOpenid(@Param("openid") String openid);
	
	//修改用户的角色
	@Update("update user_info set relo=#{relo} where openid=#{openid}")
	int update_relo(@Param("relo")int relo,
			@Param("openid")String openid);
	
	//修改用户信息，细到可以修改每一个字段
	@UpdateProvider(type=IuserinfoMapperProvider.class,method="update_info")
	Integer update_info(Map<String, Object> map);
	
	//根据用户id查询用户
	@Select("select * from user_info where id=#{userid}")
	Tab_user_info getWhereUserID(@Param("userid")String userid);
}

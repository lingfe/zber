package com.yyf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yyf.inter.Iimages;
import com.yyf.model.Tab_images;

/**
 * 
  * 文件名：IimagesMapper.java
  * 描述： 图片数据访问层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午10:10:09
  * 修改内容：
 */
public interface IimagesMapper extends Iimages {
	
	@Delete("delete FROM images where id=#{id}")
	int deleteWhereId(@Param("id")String id);

	@Select("SELECT * FROM images WHERE setId=#{setId}")
	List<Tab_images> getWhereLbtAttributeId(@Param("setId") String setId);
	
	@Insert("Insert  INTO images  "
			+ "(`id`,`full_path`,`storage_path`,`custom_name`,`old_name`,`suffix`,`setId`,`imgUrl`,`navigator`,`remark`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ " VALUES "
			+ "(#{id},#{full_path},#{storage_path},#{custom_name},#{old_name},#{suffix},#{setId},#{imgUrl},#{navigator},#{remark},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_images img);
}

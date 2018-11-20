package com.yyf.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Iprice;
import com.yyf.model.Tab_price;

/**
 * 
  * 文件名：IpriceMapper.java
  * 描述： 价格表，数据访问接口
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午8:17:04
  * 修改内容：
 */
public interface IpriceMapper extends Iprice {
	
	@Delete("delete from price where setId=#{setId}")
	int deleteWhereSetId(@Param("setId")String setId);
	
	@Select("select * from price where id=#{id}")
	Tab_price getWhereID(@Param("id")String id);

	@Update("update price set "
			+ "distribution_fee=#{distribution_fee},charging_fee=#{charging_fee},"
			+ "original_price=#{original_price},present_price=#{present_price},per_capita_price=#{per_capita_price},"
			+ "price_company=#{price_company},surplusNum=#{surplusNum},surplusNum_company=#{surplusNum_company},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_price tab);
	
	@Select("SELECT * FROM price WHERE setId=#{setId}")
	Tab_price getWhwereSetId(@Param("setId") String setId);
	
	@Insert("INSERT  INTO price "
			+ "(`id`,distribution_fee,`charging_fee`,`setId`,original_price,`present_price`,`per_capita_price`,"
			+ "price_company,surplusNum,surplusNum_company,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`)  "
			+ "VALUES "
			+ "(#{id},#{distribution_fee},#{charging_fee},#{setId},#{original_price},#{present_price},#{per_capita_price},"
			+ "#{price_company},#{surplusNum},#{surplusNum_company},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_price tab_price);
	
}

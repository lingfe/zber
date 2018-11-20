package com.yyf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.yyf.inter.Irecommend;
import com.yyf.mapperProvider.PublicMapperProvider;
import com.yyf.model.Tab_release_info;

/**
 * 
  * 文件名：Irelease_infoMapper.java
  * 描述： 发布数据信息 数据访问层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午9:07:33
  * 修改内容：
 */
public interface Irelease_infoMapper extends Irecommend<Tab_release_info> {
	
	/**
	 * 
	 * 修改
	 * @author lingfe     
	 * @created 2018年11月5日 下午6:09:38  
	 * @param tab
	 * @return
	 */
	@Update("update release_info set "
			+ "address=#{address},typeMenu_id=#{typeMenu_id},"
			+ "is_subscribe=#{is_subscribe},lable=#{lable},title=#{title},"
			+ "describe_info=#{describe_info},model=#{model},details_model=#{details_model},"
			+ "state=#{state},mdate=#{mdate},modify=#{modify},version=#{version} "
			+ "where id=#{id}")
	int update(Tab_release_info tab);
	
	/**
	 * 
	 * 保存
	 * @author lingfe     
	 * @created 2018年11月5日 下午12:44:04  
	 * @param tab
	 * @return
	 */
	@Insert("INSERT  INTO `release_info`"
			+ "(`id`,`address`,`typeMenu_id`,`is_subscribe`,`details_model`,`lable`,`title`,`model`,`describe_info`,"
			+ "`state`,`cdate`,`mdate`,`creator`,`modify`,`version`) "
			+ " VALUES "
			+ "(#{id},#{address},#{typeMenu_id},#{is_subscribe},#{details_model},#{lable},#{title},#{model},#{describe_info},"
			+ "#{state},#{cdate},#{mdate},#{creator},#{modify},#{version})")
	int save(Tab_release_info tab);
	
	/**
	 * 
	 * 根据openid得到该用户最新发布的一条项目
	 * @author lingfe     
	 * @created 2018年10月21日 上午10:52:45  
	 * @param map
	 * @return
	 */
	@Select("select * from release_info WHERE creator=#{openid} order by  mdate desc limit 0,1")
	Tab_release_info getWhereOpenId_Top1(@Param("openid")String openid);
	
	/**
	 * 
	 * 分页得到推荐的项目信息
	 * @author lingfe     
	 * @created 2018年10月21日 上午10:36:06  
	 * @param map
	 * @return
	 */
	@SelectProvider(type=PublicMapperProvider.class,method="getRecommend")
	List<Tab_release_info> getRecommend(Map<String, Object> map);

	/**
	 * 
	 * 根据openid得到发布的项目数据
	 * @author lingfe
	 * @created 2018年10月2日 下午9:11:19  
	 * @param openid
	 * @return
	 */
	@Select("SELECT * FROM release_info WHERE creator=#{openid}")
	List<Tab_release_info> getWhereOpenId(@Param("openid")String openid);
	
	/**
	 * 
	 * 分页得到发布的项目数据
	 * @author lingfe
	 * @created 2018年10月2日 下午9:11:19  
	 * @return
	 */
	@Select("SELECT * FROM release_info")
	List<Tab_release_info> getPageAll();
	
	/**
	 * 
	 * 根据项目id得到项目信息
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:26:20  
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM release_info where id=#{id}")
	Tab_release_info getWhereId(@Param("id")String id);
	
	/**
	 * 
	 * 根据分类菜单id得到项目信息
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:57:21  
	 * @param typeMenu_id
	 * @return
	 */
	@Select("SELECT * FROM release_info where typeMenu_id=#{typeMenu_id}")
	List<Tab_release_info> getWhere_TypeMenu_id(@Param("typeMenu_id")String typeMenu_id);
}

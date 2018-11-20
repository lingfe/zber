package com.yyf.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 文件名：InterJDBC.java 描述： 公共的数据访问接口 修改人： lijie 修改时间：2017年5月16日 下午4:41:43 修改内容：
 */
public interface InterJDBC<T> {

	/**
	 * 
	 * 查询轮播图数据，分页查询
	 * @author lijie    
	 * @created 2017年5月28日 上午9:09:36 
	 * @param pageIndex 当前页
	 * @param pageNum 页容量
	 */
	@Select("SELECT * FROM carouselfigure where state=0 ORDER BY mdate DESC  LIMIT #{pageIndex},#{pageNum}")
	void statusQueryPaging(@Param("pageIndex") int pageIndex,@Param("pageNum")int pageNum);
	
	/**
	 * 
	 * 查询所有
	 * 
	 * @author lijie
	 * @created 2017年5月16日 下午4:40:16
	 * @return 数据集合
	 */
	public List<T> query();

	/**
	 * 
	 * 查询行
	 * 
	 * @author lijie
	 * @created 2017年5月16日 下午4:40:56
	 * @return 结果
	 */
	public int queryCount();
}

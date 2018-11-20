package com.yyf.inter;

import java.util.List;
import java.util.Map;

/**
 * 
  * 文件名：Irecommend.java
  * 描述： 推荐数据， 接口
  * 修改人： lingfe
  * 修改时间：2018年10月12日 上午10:17:03
  * 修改内容：
 */
public interface Irecommend<T> {

	/**
	 * 
	 * 分页得到推荐数据,3条
	 * @author lingfe     
	 * @created 2018年10月11日 下午1:35:47  
	 * @param map
	 * @return
	 */
	List<T> getRecommend(Map<String, Object>  map);
}

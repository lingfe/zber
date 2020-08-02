package com.yyf.inter;

import java.util.List;

import com.yyf.model.Tab_images;

/**
 * 
  * 文件名：Iimages.java
  * 描述：图片表接口 
  * 修改人： lingfe
  * 修改时间：2018年10月27日 下午1:10:00
  * 修改内容：
 */
public interface Iimages {
	
	/**
	 * 
	 * 根据图片id标识获取图片信息
	 * @author lingfe     
	 * @created 2019年4月15日 上午11:37:44  
	 * @param id
	 * @return
	 */
	Tab_images getWhereId(String id);
	
	/**
	 * 
	 * 根据图片id删除图片
	 * @author lingfe     
	 * @created 2018年10月31日 下午7:06:09  
	 * @param id
	 * @return
	 */
	int deleteWhereId(String id);
	
	/**
	 * 
	 * 根据归属id，查询图片信息集合
	 * 根据setId得到图片数据。
	 * 设置用于哪个模块的id。比如(项目id，轮播图id):该图片来自首页轮播图，那么就用首页轮播图属性id获取图片，
	 * @author lingfe     
	 * @created 2018年10月27日 下午1:10:32  
	 * @param setId 归属id
	 * @return
	 */
	List<Tab_images> getWhereLbtAttributeId(String setId);

	/**
	 * 
	 * 保存图片信息
	 * @author lingfe     
	 * @created 2018年10月27日 下午1:11:31  
	 * @param img
	 * @return
	 */
	int save(Tab_images img);	
}

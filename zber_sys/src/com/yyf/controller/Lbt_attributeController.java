package com.yyf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.model.JosnModel;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_lbt_attribute;
import com.yyf.service.IimagesService;
import com.yyf.service.Ilbt_attributeService;

/**
 * 
  * 文件名：Lbt_attributeController.java
  * 描述： 轮播图属性，表示层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 上午9:57:55
  * 修改内容：
 */
@Controller
@RequestMapping("/lbt_attribute")
public class Lbt_attributeController {

	@Autowired 
	private Ilbt_attributeService ilbt_attributeService;
	
	@Autowired
	private IimagesService iimagesService;
	
	/**
	 * 
	 * 根据状态的轮播图属性,并返回图片
	 * @author lingfe     
	 * @created 2018年10月2日 上午10:06:51  
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/getLbtAttributeInfo", method = { RequestMethod.POST, RequestMethod.GET})
 	@ResponseBody JosnModel<Map<String, Object>> getLbtAttributeInfo(
 			@RequestParam(value="setId",required=false)String setId){
		//实例化对象
		Map<String, Object> map = new HashMap<String, Object>();
		JosnModel<Map<String, Object>> josn=new JosnModel<Map<String, Object>>();	
		
		//验证非空
		if(StringUtils.isEmpty(setId)){
			josn.msg="没有指定轮播图属性，使用默认!";
			setId="default_1";
		}
		//根据上级分类菜单id得到轮播图属性
		Tab_lbt_attribute lbt_attribute=ilbt_attributeService.getLbtAttributeInfo(setId);
		if(!StringUtils.isEmpty(lbt_attribute)){
			//根据轮播图属性id得到图片数据
			List<Tab_images> images_list =iimagesService.getWhereLbtAttributeId(lbt_attribute.getId());
			//设定返回值
			josn.msg+="成功!";
			josn.state=200;
			lbt_attribute.images_list=images_list;
			map.put("lbt_attribute", lbt_attribute);
			josn.data=map;
		}else{
			josn.msg="没有该部分的轮播图属性哦!";
		}
		
		return josn;
	}
}
	
package com.yyf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.model.JosnModel;
import com.yyf.model.Tab_type_menu;
import com.yyf.service.Itype_menuService;

/**
 * 
  * 文件名：Type_menuController.java
  * 描述： 分类菜单，表示层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午3:44:19
  * 修改内容：
 */
@Controller
@RequestMapping("/type_menu")
public class Type_menuController {

	@Autowired
	private Itype_menuService itype_menuService;
	
	/**
	 * 
	 * 根据上级id返回分类菜单数据，如果上级id为空，则返回最顶级的数据
	 * @author lingfe     
	 * @created 2018年10月2日 下午3:57:17  
	 * @param superiorId
	 * @return
	 */
	@RequestMapping( value="/getWhereSuperiorId",method = { RequestMethod.POST, RequestMethod.GET})
    @ResponseBody	JosnModel<List<Tab_type_menu>> getWhereSuperiorId(@RequestParam(value="superiorId",required=false)String superiorId){
		
		//实例化对象
		JosnModel<List<Tab_type_menu>> json=new JosnModel<List<Tab_type_menu>>();
		//验证上级id
		if(StringUtils.isEmpty(superiorId)){
			//id为空，则得到最顶级。0=表示最顶级
			json.data=itype_menuService.getWhereSuperiorId("0");
			json.msg="得到最顶级";
		}else{
			//得到superiorId 的子级分类菜单
			json.data=itype_menuService.getWhereSuperiorId(superiorId);
			json.msg="得到superiorId："+superiorId+" ，的子级分类菜单";
		}
		json.state=200;
		return json;
	}
	
}

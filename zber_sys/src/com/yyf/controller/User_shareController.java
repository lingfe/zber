package com.yyf.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.model.JosnModel;
import com.yyf.model.Tab_user_share;
import com.yyf.service.Iuser_shareService;

/**
 * 
  * 文件名：User_shareController.java
  * 描述：分享表，表示层 
  * 修改人： lingfe
  * 修改时间：2018年10月4日 下午2:54:36
  * 修改内容：
 */
@Controller
@RequestMapping("/user_share")
public class User_shareController {

	@Autowired
	private Iuser_shareService iuser_shareService;
	
	/**
	 * 
	 * 分享该项目一次
	 * @author lingfe     
	 * @created 2018年10月4日 下午3:05:19  
	 * @param project_id
	 * @return
	 */
	@RequestMapping(value="/save",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_user_share> save(@RequestParam(value="project_id",required=false)String project_id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		Tab_user_share tab_user_share=new Tab_user_share();
		JosnModel<Tab_user_share> josn=new JosnModel<Tab_user_share>();
		
		//验证非空
		if(!StringUtils.isEmpty(project_id)){
			//赋值
			tab_user_share.setId(UUID.randomUUID().toString());
			tab_user_share.setProject_id(project_id);
			tab_user_share.setCreator(openid);
			
			//执行保存
			int tt=iuser_shareService.save(tab_user_share);
			if(tt>=1){
				josn.data=tab_user_share;
				josn.state=200;
				josn.msg="分享成功";
			}else{
				josn.msg="分享失败!";
			}
			
		}else{
			josn.msg="要分享的项目id不能为空!";
		}
		
		return josn;
	}
}

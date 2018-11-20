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
import com.yyf.model.Tab_user_like;
import com.yyf.service.Iuser_likeService;

/**
 * 
  * 文件名：Project_like_numController.java
  * 描述： 项目喜欢人数表示层
  * 修改人： lingfe
  * 修改时间：2018年10月4日 上午8:56:25
  * 修改内容：
 */
@Controller
@RequestMapping("/user_like")
public class User_likeController {

	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	
	/**
	 * 
	 * 根据用户id，项目id参数得到喜欢信息
	 * @author lingfe  
	 * @created 2018年10月4日 上午9:18:50  
	 * @param project_id
	 * @param openid
	 * @return
	 */
	@RequestMapping(value="/get",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_user_like> get(
			@RequestParam(value="project_id",required=false)String project_id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<Tab_user_like> josn=new JosnModel<Tab_user_like>();
		
		//验证非空
		if(!StringUtils.isEmpty(project_id)){
			if(!StringUtils.isEmpty(openid)){
				//验证是否存在
				Tab_user_like tab_project_like_num=iproject_like_numService.getWhereOpenid(project_id, openid);
				if(!StringUtils.isEmpty(tab_project_like_num)){
					josn.state=200;
					josn.msg="get成功!";
					josn.data=tab_project_like_num;
				}else{
					josn.msg="没有喜欢过，点击爱心+喜欢!";
				}
			}else{
				josn.msg="参数错误,openid不能为空!";
			}
		}else{
			josn.msg="参数错误,项目id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 得到数据或者保存数据。当数据存在时，则修改为是,不存在时则保存!
	 * @author lingfe  
	 * @created 2018年10月4日 上午9:18:50  
	 * @param project_id
	 * @param openid
	 * @return
	 */
	@RequestMapping(value="/get_or_save",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_user_like> get_or_save(
			@RequestParam(value="project_id",required=false)String project_id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<Tab_user_like> josn=new JosnModel<Tab_user_like>();
		
		//验证非空
		if(!StringUtils.isEmpty(project_id)){
			if(!StringUtils.isEmpty(openid)){
				//验证是否存在
				Tab_user_like tab_project_like_num=iproject_like_numService.getWhereOpenid(project_id, openid);
				if(!StringUtils.isEmpty(tab_project_like_num)){
					int tt=0;
					//验证喜欢状态stats
					if(tab_project_like_num.getState() == 0){
						//设置为喜欢，1=喜欢
						josn.msg="设定为喜欢!";
						tt=iproject_like_numService.setUpdateState(project_id, openid, 1);
					}else{
						//设置为不喜欢，0=不喜欢
						josn.msg="设定为不喜欢!";
						tt=iproject_like_numService.setUpdateState(project_id, openid, 0);	
					}
					
					//验证结果
					if(tt>=1){
						//得到最新数据
						tab_project_like_num=iproject_like_numService.getWhereOpenid(project_id, openid);
						josn.data=tab_project_like_num;
						
						josn.state=200;
						josn.msg+="成功!";
					}else{
						josn.msg+="失败!";
					}
					return josn;
				}else{
					//实例化对象
					tab_project_like_num=new Tab_user_like();
					//赋值
					tab_project_like_num.setId(UUID.randomUUID().toString());
					tab_project_like_num.setProject_id(project_id);
					tab_project_like_num.setCreator(openid);
					josn.data=tab_project_like_num;
					
					//得到返回值
					int tt=iproject_like_numService.save(tab_project_like_num);
					if(tt>=1){
						josn.state=200;
						josn.msg="喜欢成功!";
					}else{
						josn.msg="喜欢失败!";
					}
				}
				
			}else{
				josn.msg="参数错误,openid不能为空!";
			}
		}else{
			josn.msg="参数错误,项目id不能为空!";
		}
		
		return josn;
	}
	
}

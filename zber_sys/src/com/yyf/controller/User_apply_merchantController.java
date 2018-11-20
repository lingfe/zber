package com.yyf.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.model.JosnModel;
import com.yyf.model.Tab_user_apply_merchant;
import com.yyf.service.Iuser_apply_merchantService;

@Controller
@RequestMapping("/user_apply_merchant")
public class User_apply_merchantController {
	
	@Autowired
	private Iuser_apply_merchantService iuser_apply_merchantService;
		
	/**
	 * 
	 * 根据状态获取用户申请商户信息集合
	 * @author lingfe     
	 * @created 2018年10月6日 下午10:04:56  
	 * @param state 状态
	 * @param id 
	 * @param openid 修改人
	 * @return
	 */
	@RequestMapping(value="/getWhereState",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody	
	JosnModel<List<Tab_user_apply_merchant>> getWhereState(
			@RequestParam(value="state",required=false)Integer state){
		//实例化对象
		JosnModel<List<Tab_user_apply_merchant>> josn=new JosnModel<List<Tab_user_apply_merchant>>();
		if(!StringUtils.isEmpty(state)){
			//得到data
			josn.state=200;
			josn.msg="成功!";
			josn.data=iuser_apply_merchantService.getWhereState(state);
			
		}else{
			josn.msg="参数错误!";
		}
		return josn;
	}
	
	
	/**
	 * 
	 * 修改状态
	 * @author lingfe     
	 * @created 2018年10月6日 下午10:04:56  
	 * @param state 状态
	 * @param id 
	 * @param openid 修改人
	 * @return
	 */
	@RequestMapping(value="/updateState",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody	
	JosnModel<Tab_user_apply_merchant> updateState(
			@RequestParam(value="state",required=false)int state,
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<Tab_user_apply_merchant> josn=new JosnModel<Tab_user_apply_merchant>();
		//验证参数
		if(!StringUtils.isEmpty(id)){
			if(!StringUtils.isEmpty(openid)){	
				//根据id得到信息
				Tab_user_apply_merchant tab=iuser_apply_merchantService.getWhereId(id);
				if(!StringUtils.isEmpty(tab)){
					//版本
					int version=Integer.parseInt(tab.getVersion())+1;
					//执行修改状态
					int tt=iuser_apply_merchantService.updateState(state, String.valueOf(version), id, openid);
					
					if(tt>=1){
						josn.state=200;
						josn.msg="修改成功!";
						josn.data=iuser_apply_merchantService.getWhereId(id);
					}else{
						josn.msg="修改失败!";
					}
				}else{
					josn.msg="id无效！请检查!";
				}
			}else{
				josn.msg="openid不能为空!请检查是不是回话过期了?";
			}
		}else{
			josn.msg="id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 申请成为商户，保存用户申请信息
	 * @author lingfe     
	 * @created 2018年10月6日 下午4:46:08  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody	
	JosnModel<Tab_user_apply_merchant> save(Tab_user_apply_merchant tab){
		//实例化对象
		JosnModel<Tab_user_apply_merchant> josn=new JosnModel<Tab_user_apply_merchant>();
		//验证非空
		if(!StringUtils.isEmpty(tab.getCreator())){
			//根据创建者，openid查询是否已经申请过
			Tab_user_apply_merchant tab_user_apply_merchant = iuser_apply_merchantService.getWhereOpenid(tab.getCreator());
			if(StringUtils.isEmpty(tab_user_apply_merchant)){
				//赋值
				tab.setId(UUID.randomUUID().toString());
				//保存
				int tt=iuser_apply_merchantService.save(tab);
				if(tt>=1){
					josn.state=200;
					josn.msg="申请提交成功!请等待审核!我们的客服会通过电话告知您，如果电话无法接通请您主动联系客服!17310677075";
					josn.data=tab;
				}else{
					josn.msg="申请提交成功失败!";
				}
			}else{
				josn.state=200;
				josn.msg="您已经申请过，请等待审核!或者可以去个人中心编辑";
			}
		}else{
			josn.state=401;
			josn.msg="openid不能为空!";
		}
		
		return josn;
	}

	
	
}

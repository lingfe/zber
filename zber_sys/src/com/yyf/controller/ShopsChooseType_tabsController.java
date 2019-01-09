package com.yyf.controller;

import java.util.Date;
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
import com.yyf.model.Tab_shopsChooseType_tabs;
import com.yyf.service.IshopsChooseType_tabsService;

@Controller
@RequestMapping("/shopsChooseType_tabs")
public class ShopsChooseType_tabsController {

	@Autowired
	private IshopsChooseType_tabsService ishopsChooseType_tabsService;
	
	/**
	 * 
	 * 根据选购tabs菜单id修改编辑
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:44:37  
	 * @param id
	 * @param modify
	 * @param tabs_name
	 * @return
	 */
	@RequestMapping(value="/updateWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shopsChooseType_tabs>> updateWhereId(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="modify",required=false)String modify,
			@RequestParam(value="tabs_name",required=false)String tabs_name){
		
		//实例化对象
		JosnModel<List<Tab_shopsChooseType_tabs>> josn=new JosnModel<List<Tab_shopsChooseType_tabs>>();
		if(!StringUtils.isEmpty(id)){
			if(!StringUtils.isEmpty(modify)){
				//根据id得到商铺选购tabs分类菜单
				Tab_shopsChooseType_tabs chooseType_tabs=ishopsChooseType_tabsService.getWhereId(id);
				if(!StringUtils.isEmpty(chooseType_tabs)){
					chooseType_tabs.setMdate(new Date());
					chooseType_tabs.setModify(modify);
					chooseType_tabs.setTabs_name(tabs_name);
					chooseType_tabs.setVersion(String.valueOf(Integer.parseInt(chooseType_tabs.getVersion())+1));
					//执行修改
					int tt=ishopsChooseType_tabsService.updateWhereId(chooseType_tabs);
					if(tt>0){
						josn.state=200;
						josn.msg="修改成功!";
						//再根据商铺id查询
						josn.data=ishopsChooseType_tabsService.getWhereShopsId(chooseType_tabs.getShops_id());
					}else{
						josn.msg="修改失败!";
					}
				}else{
					josn.msg="选购分类tabs菜单id无效，或者已经被删除？";
				}
			}else{
				josn.state=401;
				josn.msg="修改人不能为空!";
			}
			
		}else{
			josn.msg="选购分类菜单id不能为空";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 根据选购分类tabs菜单id删除
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:26:42  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shopsChooseType_tabs>> deleteWhereId(@RequestParam(value="id",required=false)String id){
		//实例化 对象
		JosnModel<List<Tab_shopsChooseType_tabs>> josn=new JosnModel<List<Tab_shopsChooseType_tabs>>();
		if(!StringUtils.isEmpty(id)){
			Tab_shopsChooseType_tabs info=ishopsChooseType_tabsService.getWhereId(id);
			//执行删除
			int tt =ishopsChooseType_tabsService.deleteWhereId(id);
			if(tt>=1){
				josn.state=200;
				josn.msg="删除成功!";
				//重新根据商铺id得到选购分类菜单tabs
				josn.data=ishopsChooseType_tabsService.getWhereShopsId(info.getShops_id());
			}else{
				josn.msg="删除失败!";
			}
		}else{
			josn.msg="选购分类tabs菜单id不能为空!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 根据shops_id得到商铺选购分类tabs集合
	 * @author lingfe     
	 * @created 2018年10月7日 下午9:09:37  
	 * @param shops_id
	 * @return
	 */
	@RequestMapping(value="/getWhereShopsId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shopsChooseType_tabs>> getWhereShopsId(@RequestParam(value ="shops_id",required=false)String shops_id){
		//实例化对象
		JosnModel<List<Tab_shopsChooseType_tabs>> josn=new JosnModel<List<Tab_shopsChooseType_tabs>>();
		if(!StringUtils.isEmpty(shops_id)){
			try {
				//根据shops_id得到商铺选购分类tabs集合
				josn.data=ishopsChooseType_tabsService.getWhereShopsId(shops_id);
				josn.msg="获取成功!";
				josn.state=200;
			} catch (Exception e) {
				josn.msg=e.getMessage();
				e.printStackTrace();
			}
			
		}else{
			josn.msg="商铺id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 保存一条商铺选购分类tabs 数据,并返回最新的数据
	 * @author lingfe     
	 * @created 2018年10月7日 下午9:01:07  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shopsChooseType_tabs>> save(Tab_shopsChooseType_tabs tab){
		//实例化对象
		JosnModel<List<Tab_shopsChooseType_tabs>> josn=new JosnModel<List<Tab_shopsChooseType_tabs>>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			if(!StringUtils.isEmpty(tab.getShops_id())){
				//赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				//执行保存
				int tt=ishopsChooseType_tabsService.save(tab);
				if(tt>=1){
					josn.state = 200;
					josn.msg="保存成功!";
					//重新根据商铺id得到选购分类菜单tabs
					josn.data=ishopsChooseType_tabsService.getWhereShopsId(tab.getShops_id());
				}else{
					josn.msg="保存失败!";
				}
			}else{
				josn.msg="商铺id不能为空!";
			}
		}else{
			josn.state =401;
			josn.msg="openid不能为空!";
		}
		
		return josn;
	}
}

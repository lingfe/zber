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
import com.yyf.model.Tab_tabs;
import com.yyf.service.ItabsService;

@Controller
@RequestMapping("/tabs")
public class TabsController {

	@Autowired
	private ItabsService itabsService;
	
	/**
	 * 
	 *  保存导航tabs菜单
	 * @author lingfe     
	 * @created 2018年10月8日 下午3:03:06  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_tabs>> save(Tab_tabs tab){
		//实例化对象
		JosnModel<List<Tab_tabs>> josn=new JosnModel<List<Tab_tabs>>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			//赋值
			tab.setId(UUID.randomUUID().toString().replace("-",	""));
			//保存
			int tt=itabsService.save(tab);
			if(tt>=1){
				josn.msg="保存成功!";
				josn.state=200;
				//根据商铺id得到导航菜单
				josn.data=itabsService.getWhere_project_id(tab.getProject_id());
			}else{
				josn.msg="保存失败!";
			}
		}else{
			josn.state=401;
			josn.msg="创建人不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 根据商铺id得到tabs导航菜单
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:59:26  
	 * @param shops_id
	 * @return
	 */
	@RequestMapping(value="/getWhereShops_id",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_tabs>> getWhereShops_id(@RequestParam(value="shops_id",required=false)String shops_id){
		
		//实例化对象
		JosnModel<List<Tab_tabs>> josn=new JosnModel<List<Tab_tabs>>();
		if(!StringUtils.isEmpty(shops_id)){
			try {
				//根据商铺id得到tabs导航菜单
				josn.data=itabsService.getWhere_project_id(shops_id);
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
	 * 根据tabs导航菜单id修改编辑
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:44:37  
	 * @param id
	 * @param modify
	 * @param tabs_name
	 * @return
	 */
	@RequestMapping(value="/updateWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_tabs>> updateWhereId(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="modify",required=false)String modify,
			@RequestParam(value="tabs_name",required=false)String tabs_name,
			@RequestParam(value="content",required=false)String content){
		
		//实例化对象
		JosnModel<List<Tab_tabs>> josn=new JosnModel<List<Tab_tabs>>();
		if(!StringUtils.isEmpty(id)){
			if(!StringUtils.isEmpty(modify)){
				//根据tabs导航菜单id查询数据
				Tab_tabs tabs=itabsService.getWhereId(id);
				if(!StringUtils.isEmpty(tabs)){
					tabs.setMdate(new Date());
					tabs.setModify(modify);
					tabs.setTabs_name(tabs_name);
					tabs.setContent(content);
					tabs.setVersion(String.valueOf(Integer.parseInt(tabs.getVersion())+1));
					//执行修改
					int tt=itabsService.updateWhereId(tabs);
					if(tt>0){
						josn.state=200;
						josn.msg="修改成功!";
						josn.data=itabsService.getWhere_project_id(tabs.getProject_id());
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
	 * 根据tabs菜单id删除
	 * @author lingfe     
	 * @created 2018年10月8日 上午10:26:42  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Object> deleteWhereId(@RequestParam(value="id",required=false)String id){
		//实例化 对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(id)){
			Tab_tabs info=itabsService.getWhereId(id);
			//执行删除
			int tt =itabsService.deleteWhereId(id);
			if(tt>=1){
				josn.state=200;
				josn.msg="删除成功!";
				//根据商铺id得到tabs导航菜单
				josn.data=itabsService.getWhere_project_id(info.getProject_id());
			}else{
				josn.msg="删除失败!";
			}
		}else{
			josn.msg="选购分类tabs菜单id不能为空!";
		}
		
		return josn;
	}
	
	
}

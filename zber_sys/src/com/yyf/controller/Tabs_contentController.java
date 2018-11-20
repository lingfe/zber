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

import com.yyf.controller.util.SYS_GET;
import com.yyf.model.JosnModel;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_tabs_content;
import com.yyf.service.IimagesService;
import com.yyf.service.Itabs_contentService;

/**
 * 
  * 文件名：Tabs_contentController.java
  * 描述：  表示层
  * 修改人： lingfe
  * 修改时间：2018年11月17日 下午4:30:08
  * 修改内容：
 */
@Controller
@RequestMapping("/tabs_content")
public class Tabs_contentController {

	@Autowired
	private Itabs_contentService itabs_contentService;
	
	@Autowired 
	private IimagesService iimagesService;
	
	/**
	 * 
	 * 根据tabs导航菜单内容id删除
	 * @author lingfe     
	 * @created 2018年11月19日 上午10:41:58  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Object> deleteWhereId(@RequestParam(value="id",required=false)String id){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(id)){
			//执行删除
			int tt=itabs_contentService.deleteWhereId(id);
			if(tt>=1){
				josn.msg="删除成功!";
				josn.state=200;
			}else{
				josn.msg="删除失败!";
			}
		}
		
		return josn;
	}
	
	/**
	 * 根据getId查询数据,得到tabs导航菜单内容
	 * @author lingfe     
	 * @created 2018年11月17日 下午4:53:15  
	 * @param get_id
	 * @return
	 */
	@RequestMapping(value="/getWhereGetID",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Object> getWhereGetID(@RequestParam(value="get_id",required=false)String get_id){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		
		if(!StringUtils.isEmpty(get_id)){
			//执行查询
			List<Tab_tabs_content> list=itabs_contentService.getWhereGetID(get_id);
			for (Tab_tabs_content tab_tabs_content : list) {
				//得到图片
				List<Tab_images> images_list=iimagesService.getWhereLbtAttributeId(tab_tabs_content.getId());
				for (Tab_images tab_images : images_list) {
					//验证路径
					if(tab_images.getImgUrl().indexOf("http") ==-1){
    					String imgUrl= SYS_GET.GET_IMG_PATH_URL + tab_images.getImgUrl();
        				tab_images.setImgUrl(imgUrl);
    				}
				}
				tab_tabs_content.images_list=images_list;
			}
			
			josn.msg="get成功!";
			josn.state=200;
			josn.data=list;
		}else{
			josn.msg="getid不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 *  保存或修改导航tabs菜单内容
	 * @author lingfe     
	 * @created 2018年10月8日 下午3:03:06  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save_or_update",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Tab_tabs_content> save_or_update(Tab_tabs_content tab){
		//实例化对象
		JosnModel<Tab_tabs_content> josn=new JosnModel<Tab_tabs_content>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			//验证id是否为空
			if(!StringUtils.isEmpty(tab.getId())){
				//执行查询
				Tab_tabs_content info=itabs_contentService.getWhereId(tab.getId());
				if(!StringUtils.isEmpty(info)){
					//编辑，赋值
					tab.setMdate(new Date());
					tab.setModify(tab.getCreator());
					tab.setVersion(String.valueOf(Integer.parseInt(info.getVersion())+1));
					
					//执行修改
					int tt=itabs_contentService.update(tab);
					if(tt>=1){
						josn.msg="修改成功!";
						josn.data=tab;
						josn.state=200;
					}else{
						josn.msg="修改失败!";
					}
				}else{
					josn.msg="id无效!请检查是否已经删除了!~";
				}
			}else{
				//保存,赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				
				//执行保存
				int tt=itabs_contentService.save(tab);
				if(tt>=1){
					josn.msg="保存成功!";
					josn.state=200;
					josn.data=tab;
				}else{
					josn.msg="保存失败!";
				}
				
			}
		}else{
			josn.msg="创建者openid不能为空!";
			josn.state=401;
		}
		
		return josn;
	}
}

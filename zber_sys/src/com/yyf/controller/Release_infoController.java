package com.yyf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.controller.util.SYS_GET;
import com.yyf.mapper.IimagesMapper;
import com.yyf.mapper.IuserinfoMapper;
import com.yyf.model.JosnModel;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_lbt_attribute;
import com.yyf.model.Tab_price;
import com.yyf.model.Tab_release_info;
import com.yyf.model.Tab_tabs;
import com.yyf.model.Tab_user_browse;
import com.yyf.model.Tab_user_follow;
import com.yyf.model.Tab_user_info;
import com.yyf.service.Ilbt_attributeService;
import com.yyf.service.IpriceService;
import com.yyf.service.Irelease_infoService;
import com.yyf.service.ItabsService;
import com.yyf.service.Iuser_browseService;
import com.yyf.service.Iuser_followService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.Iuser_shareService;

/**
 * 
  * 文件名：Release_infoController.java
  * 描述： 发布数据信息,表示层
  * 修改人： lingfe
  * 修改时间：2018年10月2日 下午9:19:53
  * 修改内容：
 */
@Controller
@RequestMapping("/release_info")
public class Release_infoController {

	@Autowired
	private Irelease_infoService irelease_infoService;
	
	@Autowired
	private IpriceService ipriceService;
	
	@Autowired
	private ItabsService itabsService;
	
	@Autowired 
	private IuserinfoMapper iuserinfoMapper;
	
	@Autowired 
	private IimagesMapper iimagesMapper;
	
	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	@Autowired
	private Iuser_shareService iuser_shareService;
	
	@Autowired
	private Iuser_browseService iuser_browseService;
	
	@Autowired 
	private Ilbt_attributeService ilbt_attributeService;
	
	@Autowired
	private Iuser_followService iuser_followService;
	
	/**
	 * 
	 * 保存或修改发布的项目信息
	 * @author lingfe     
	 * @created 2018年11月5日 下午1:01:49  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save_or_update",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_release_info> save_or_update(Tab_release_info tab){
		//实例化对象
		JosnModel<Tab_release_info> josn=new JosnModel<Tab_release_info>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			//验证是编辑还是添加
			if(!StringUtils.isEmpty(tab.getId())){
				//修改
				//查询
				Tab_release_info info=irelease_infoService.getWhereId(tab.getId());
				if(!StringUtils.isEmpty(info)){
					//赋值
					tab.setMdate(new Date());
					tab.setModify(tab.getCreator());
					tab.setCreator(info.getCreator());
					tab.setModel(tab.getModel());
					tab.setVersion((Integer.parseInt(info.getVersion())+1)+"");
					
					//执行修改
					int tt=irelease_infoService.update(tab);
					if(tt>=1){
						josn.state=200;
						josn.data=tab;
						josn.msg="修改成功!";
					}else{
						josn.msg="修改失败!";
					}
				}else{
					josn.msg="id无效,修改失败!请检查!";
				}
			}else{
				//添加
				//赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				tab.setCdate(new Date());
				tab.setMdate(tab.getCdate());
				tab.setModify(tab.getCreator());
				tab.setVersion("1");
				//随机数
				Random rand = new Random();
				int model= rand.nextInt(7);
				tab.setModel(model);
				//结果
				model=irelease_infoService.save(tab);
				if(model>=1){
					josn.state=200;
					josn.data=tab;
					josn.msg="保存成功!";
				}else{
					josn.msg="保存失败!";
				}
			}
		}else{
			josn.state=401;
			josn.msg="openid不能为空!";
		}
		return josn;
	}
	
	
	/**
	 * 
	 * 根据分类菜单id得到项目信息
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:26:20  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getWhere_TypeMenu_id",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Map<String, Object>> getWhere_TypeMenu_id(
			@RequestParam(value="typeMenu_id", required=false)String typeMenu_id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		Map<String, Object> map = new HashMap<String, Object>();
		List<Tab_release_info> lists = new ArrayList<Tab_release_info>();
		JosnModel<Map<String, Object>> josn=new JosnModel<Map<String, Object>>();

		//验证
	    if(!StringUtils.isEmpty(typeMenu_id)){
	    	//得到项目信息
		    List<Tab_release_info> release_info_list=irelease_infoService.getWhere_TypeMenu_id(typeMenu_id);
    		//判断标签是否为空
    		if(!StringUtils.isEmpty(release_info_list)){ 			
    			//循环遍历
    			for (Tab_release_info tab_release_info : release_info_list) {							
        			//根据项目id得到项目价格信息
            		Tab_price price=ipriceService.getWhwereSetId(tab_release_info.getId());
            		if(!StringUtils.isEmpty(price)){
            			tab_release_info.price=price;
            		}
            		
            		//根据项目创建者得到发布者信息
            		Tab_user_info user_info=iuserinfoMapper.getWhereOpenid(tab_release_info.getCreator());
            		if(!StringUtils.isEmpty(user_info)){
            			//根据创建者用户与当前访问用户得到是否关注信息
    	    			Tab_user_follow user_follow= this.getUserFollow(user_info.getId(), openid);
    	    			if(!StringUtils.isEmpty(user_follow)){
    	    				user_info.user_follow=user_follow;
    	    			}else{
    	    				user_info.user_follow=new Tab_user_follow();
    	    			}
    	    			tab_release_info.user_info=user_info;
            		}
            		
            		//根据项目id得到项目图片数据
            		List<Tab_images> images=iimagesMapper.getWhereLbtAttributeId(tab_release_info.getId());
            		if(!StringUtils.isEmpty(images)){
            			tab_release_info.images=images;
            		}
            		
            		//根据项目id得到该项目喜欢的人数
            		int like_num= iproject_like_numService.getProjectLikeNum(tab_release_info.getId());
            		tab_release_info.setLike_num(like_num);
            		
            		//根据项目id得到分享次数
            		int share_num=iuser_shareService.getCount(tab_release_info.getId());
            		tab_release_info.setShare_num(share_num);
            		
            		//根据项目id得到浏览次数
            		int browse_num=iuser_browseService.getConut(tab_release_info.getId());
            		tab_release_info.setBrowse_num(browse_num);

        			//保存数据
            		lists.add(tab_release_info);
				}
    		}
			//保存项目信息
			map.put("lists", lists);
			
    		josn.msg="成功!";
	    	josn.data=map;
	    	josn.state=200;
	    }else{
	    	josn.msg="分类菜单id不能为空!";
	    }
		
		return josn;
	}
	//根据被关注者与关注者得到关注信息
	Tab_user_follow getUserFollow(String user_id,String openid){
		if(!StringUtils.isEmpty(user_id)){
			if(!StringUtils.isEmpty(openid)){
				//执行查询
				return iuser_followService.getWhereUserID(user_id, openid);
			}
			return null;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 
	 * 根据项目id得到项目信息,得到详情
	 * @author lingfe     
	 * @created 2018年10月3日 上午12:26:20  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Map<String, Object>> getWhereId(
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		Map<String, Object> map = new HashMap<String, Object>();
		JosnModel<Map<String, Object>> josn=new JosnModel<Map<String, Object>>();
		
		//用户lable集合
		List<String> lable_list=new ArrayList<String>();

		//验证
	    if(!StringUtils.isEmpty(id)){
	    	//得到项目id得到信息
		    Tab_release_info tab_release_info=irelease_infoService.getWhereId(id);
    		//判断是否为空
    		if(!StringUtils.isEmpty(tab_release_info)){ 	
    			//验证标签是否为空
    			if(!StringUtils.isEmpty(tab_release_info.getLable())){
    				//lable
        			String[] lable_arr=tab_release_info.getLable().split(",");
        			for (String lable : lable_arr) {
        				lable_list.add(lable);
    				}
    			}
    			
    			
    			//根据项目id得到项目价格信息
        		Tab_price price_info=ipriceService.getWhwereSetId(tab_release_info.getId());
        		if(!StringUtils.isEmpty(price_info)){
        			//保存数据，并添加标签
        			lable_list.add("价格"+price_info.getPresent_price()+"/"+price_info.getPrice_company());
        			lable_list.add("剩余"+price_info.getSurplusNum()+"/"+price_info.getSurplusNum_company());
        			
        			map.put("price_info", price_info);
        		}
        		
        		//根据项目创建者得到发布者信息
        		Tab_user_info user_info=iuserinfoMapper.getWhereOpenid(tab_release_info.getCreator());
        		if(!StringUtils.isEmpty(user_info)){
        			map.put("user_info", user_info);
        		}
        		
        		//根据项目id得到详情轮播图属性
    			Tab_lbt_attribute lbt_attribute= ilbt_attributeService.getLbtAttributeInfo(tab_release_info.getId());
    			if(!StringUtils.isEmpty(lbt_attribute)){
    				//根据项目id得到项目图片数据
            		List<Tab_images> images_list=iimagesMapper.getWhereLbtAttributeId(tab_release_info.getId());
            		if(!StringUtils.isEmpty(images_list)){
            			lbt_attribute.images_list=images_list;
            			tab_release_info.images=images_list;
            			map.put("lbt_attribute", lbt_attribute);
            		}
    			}else{
    				//根据项目id得到详情轮播图属性,使用默认轮播图属性default_1
        			lbt_attribute= ilbt_attributeService.getLbtAttributeInfo("default_1");
        			//根据项目id得到项目图片数据
            		List<Tab_images> images_list=iimagesMapper.getWhereLbtAttributeId(tab_release_info.getId());
            		if(images_list.size()<=0){
            			//根据项目id得到项目图片数据,使用默认图片default_1
                		images_list=iimagesMapper.getWhereLbtAttributeId("default_1");
            		}
            		if(!StringUtils.isEmpty(images_list)){
            			lbt_attribute.images_list=images_list;
            			tab_release_info.images=images_list;
            			map.put("lbt_attribute", lbt_attribute);
            		}
    				
    			}
        		
        		
        		//根据项目id得到tabs菜单数据
        		List<Tab_tabs> tabs_list=itabsService.getWhere_project_id(tab_release_info.getId());
        		if(!StringUtils.isEmpty(tabs_list)){
        			map.put("tabs_list", tabs_list);
        		}
        		
        		//根据项目id得到该项目喜欢的人数
        		int like_num= iproject_like_numService.getProjectLikeNum(tab_release_info.getId());
        		tab_release_info.setLike_num(like_num);
        		lable_list.add(tab_release_info.getLike_num() +"人喜欢");
        		
        		//根据项目id得到分享次数
        		int share_num=iuser_shareService.getCount(tab_release_info.getId());
        		tab_release_info.setShare_num(share_num);
        		lable_list.add(tab_release_info.getShare_num() +"次分享");
        		
        		//根据项目id得到浏览次数
        		int browse_num=iuser_browseService.getConut(tab_release_info.getId());
        		tab_release_info.setBrowse_num(browse_num);
        		lable_list.add(tab_release_info.getBrowse_num() +"次浏览");
        		
        		//浏览++
        		Tab_user_browse tab_user_browse=new Tab_user_browse();
        		tab_user_browse.setCreator(openid);
        		tab_user_browse.setProject_id(tab_release_info.getId());
        		iuser_browseService.save(tab_user_browse);
    			
        		//保存标签
        		map.put("lable_list", lable_list);
        		//保存项目信息
    			map.put("tab_release_info",tab_release_info);
    		}
			
    		josn.msg="成功!";
	    	josn.data=map;
	    	josn.state=200;
	    }else{
	    	josn.msg="项目id不能为空!";
	    }
		
		return josn;
	}
	
	
	/**
	 * 
	 * 根据openid得到项目信息
	 * @author lingfe     
	 * @created 2018年11月15日 上午11:57:40  
	 * @return
	 */
	@RequestMapping(value="/getWhereOpenid",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Object> getWhereOpenid(
			@RequestParam(value="openid",required=false)String openid){
		
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(openid)){
			//得到项目信息
		    List<Tab_release_info> release_info_List=irelease_infoService.getWhereOpenId(openid);
		    if(!StringUtils.isEmpty(release_info_List)){
		    	//遍历
		    	for (Tab_release_info releaseInfo : release_info_List) {
		    		//根据项目id得到项目图片数据
	        		List<Tab_images> images=iimagesMapper.getWhereLbtAttributeId(releaseInfo.getId());
	        		if(!StringUtils.isEmpty(images)){
	        			//遍历图片数据
	        			for (Tab_images tab_images : images) {
	        				if(tab_images.getImgUrl().indexOf("http") ==-1){
	        					String imgUrl= SYS_GET.GET_IMG_PATH_URL + tab_images.getImgUrl();
		        				tab_images.setImgUrl(imgUrl);
	        				}
						}
	        			releaseInfo.images=images;
	        		}
	        		
	        		//根据项目id得到该项目喜欢的人数
	        		int like_num= iproject_like_numService.getProjectLikeNum(releaseInfo.getId());
	        		releaseInfo.setLike_num(like_num);
	        		
	        		//根据项目id得到分享次数
	        		int share_num=iuser_shareService.getCount(releaseInfo.getId());
	        		releaseInfo.setShare_num(share_num);
					
					//根据项目id得到价格信息
					Tab_price price= ipriceService.getWhwereSetId(releaseInfo.getId());
					if(!StringUtils.isEmpty(price)){
		    			releaseInfo.price=price;
		    		}
					
					//根据项目创建者得到用户信息
		    		Tab_user_info user_info=iuserinfoMapper.getWhereOpenid(releaseInfo.getCreator());
		    		if(!StringUtils.isEmpty(user_info)){
		    			//验证路径
		    			if(user_info.getAvatar().indexOf("http") ==-1){
        					String imgUrl= SYS_GET.GET_IMG_PATH_URL + user_info.getAvatar();
        					user_info.setAvatar(imgUrl);
        				}
		    			//根据创建者用户与当前访问用户得到是否关注信息
		    			Tab_user_follow user_follow= this.getUserFollow(user_info.getId(), openid);
		    			if(!StringUtils.isEmpty(user_follow)){
		    				user_info.user_follow=user_follow;
		    			}else{
		    				user_info.user_follow=new Tab_user_follow();
		    			}
		    			releaseInfo.user_info=user_info;
		    		}
				}
        		
		    	josn.msg="成功!";
		    	josn.data=release_info_List;
		    	josn.state=200;
		    }else{
		    	josn.msg="没有内容!";
		    }
		}else{
			josn.state=401;
			josn.msg="openid不能为空!请登录!";
		}
		

		return josn;
	}
	
}

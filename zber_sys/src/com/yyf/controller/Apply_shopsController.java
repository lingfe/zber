package com.yyf.controller;

import java.util.ArrayList;
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
import com.yyf.mapper.IimagesMapper;
import com.yyf.mapper.IuserinfoMapper;
import com.yyf.model.JosnModel;
import com.yyf.model.Tab_apply_shops;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_price;
import com.yyf.model.Tab_shops_commodity;
import com.yyf.model.Tab_tabs;
import com.yyf.model.Tab_tabs_content;
import com.yyf.model.Tab_user_browse;
import com.yyf.model.Tab_user_follow;
import com.yyf.model.Tab_user_info;
import com.yyf.service.Iapply_shopsService;
import com.yyf.service.IpriceService;
import com.yyf.service.IshopsChooseType_tabsService;
import com.yyf.service.Ishops_commodityService;
import com.yyf.service.ItabsService;
import com.yyf.service.Itabs_contentService;
import com.yyf.service.Iuser_browseService;
import com.yyf.service.Iuser_followService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.Iuser_shareService;

@Controller
@RequestMapping("/apply_shops")
public class Apply_shopsController {
	
	@Autowired
	private Iapply_shopsService iapply_shopsService;
	
	@Autowired 
	private IimagesMapper iimagesMapper;
	
	@Autowired
	private ItabsService itabsService;
	
	@Autowired
	private Ishops_commodityService ishops_commodityService;
	
	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	@Autowired
	private Iuser_shareService iuser_shareService;
	
	@Autowired
	private Iuser_browseService iuser_browseService;
	
	@Autowired 
	private IuserinfoMapper iuserinfoMapper;
	
	@Autowired
	private IpriceService ipriceService;
	
	@Autowired
	private IshopsChooseType_tabsService ishopsChooseType_tabsService;
	
	@Autowired
	private Iuser_followService iuser_followService;

	@Autowired
	private Itabs_contentService itabs_contentService;
	
	/**
	 * 
	 * 根据分类菜单id查询，all商铺
	 * @author lingfe     
	 * @created 2018年10月9日 上午11:47:00  
	 * @param type_menu_id
	 * @return
	 */
	@RequestMapping(value="/getWhere_type_menu_id", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<List<Tab_apply_shops>> getWhere_type_menu_id(
			@RequestParam(value="type_menu_id",required=false)String type_menu_id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<List<Tab_apply_shops>> josn=new JosnModel<List<Tab_apply_shops>>();
		josn.data=new ArrayList<Tab_apply_shops>();
		
		if(!StringUtils.isEmpty(type_menu_id)){
			//根据分类菜单id查询，商铺
			List<Tab_apply_shops> list=iapply_shopsService.getWhere_type_menu_id(type_menu_id);
			if(!StringUtils.isEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					Tab_apply_shops shops =list.get(i);
					
					//根据商铺id得到商铺价格参数
					shops.shops_price=ipriceService.getWhwereSetId(shops.getId());
					
					//根据商铺id得到该商铺的推荐商品
					List<Tab_shops_commodity> commodity_list=ishops_commodityService.getWhere_shops_recommend(shops.getId());			
					for (int j = 0; j < commodity_list.size(); j++) {
						Tab_shops_commodity commodity=commodity_list.get(j);
						//得到该商品的价格参数
						commodity.price=ipriceService.getWhwereSetId(commodity.getId());
						commodity_list.set(j, commodity);
					}
					shops.commodity_list=commodity_list;
					
					//根据商铺创建者得到用户信息
            		Tab_user_info user_info=iuserinfoMapper.getWhereOpenid(shops.getCreator());
            		if(!StringUtils.isEmpty(user_info)){
            			//根据创建者用户与当前访问用户得到是否关注信息
    	    			Tab_user_follow user_follow= this.getUserFollow(user_info.getId(), openid);
    	    			if(!StringUtils.isEmpty(user_follow)){
    	    				user_info.user_follow=user_follow;
    	    			}else{
    	    				user_info.user_follow=new Tab_user_follow();
    	    			}
            			shops.user_info=user_info;
            		}
            		
            		//根据商铺id得到该项目喜欢的人数
            		int like_num= iproject_like_numService.getProjectLikeNum(shops.getId());
            		shops.like_num=like_num;
            		
            		//根据商铺id得到分享次数
            		int share_num=iuser_shareService.getCount(shops.getId());
            		shops.share_num=share_num;
            		
            		//根据商铺id得到浏览次数
            		int browse_num=iuser_browseService.getConut(shops.getId());
            		shops.browse_num=browse_num;
            		
            		//保存数据
            		josn.data.add(shops);
				}
				
				josn.state=200;
				josn.msg="获取成功!";
			}else{
				josn.msg="没有内容!";
			}
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
	 * 保存该用户的商铺信息
	 * @author lingfe     
	 * @created 2018年10月7日 下午5:36:19  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save_or_update", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_apply_shops> save_or_update(Tab_apply_shops tab){
		//实例化对象
		JosnModel<Tab_apply_shops> josn=new JosnModel<Tab_apply_shops>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			if(StringUtils.isEmpty(tab.getId())){
				//赋值
				tab.setId(UUID.randomUUID().toString());
				//保存
				int tt=iapply_shopsService.save(tab);
				if(tt>=1){
					josn.state=200;
					josn.msg="保存成功!";
					josn.data=tab;
				}else{
					josn.msg="保存失败!";
				}
			}else{
				//id不为空，则进行编辑
				tab.setVersion(String.valueOf(Integer.parseInt(tab.getVersion())+1));
				tab.setMdate(new Date());
				
				//执行编辑
				int tt=iapply_shopsService.updateWhereId(tab);
				if(tt>=1){
					josn.msg="修改成功!";
					josn.state=200;
					josn.data=tab;
				}else{
					josn.msg="修改失败!";
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
	 * 根据openid查询商铺信息
	 * @author lingfe     
	 * @created 2018年10月7日 下午5:40:37  
	 * @return
	 */
	@RequestMapping(value="/getWhereOpenid", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<List<Tab_apply_shops>> getWhereOpenid(@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<List<Tab_apply_shops>> josn=new JosnModel<List<Tab_apply_shops>>();
		if(!StringUtils.isEmpty(openid)){
			//根据openid得到商铺信息
			List<Tab_apply_shops> list= iapply_shopsService.getWhereOpenid(openid);
			
			
			josn.state=200;
			josn.msg="获取成功!";
			josn.data=list;
			
		}else{
			josn.state=401;
			josn.msg="openid不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 根据商铺id得到商铺详情信息
	 * @author lingfe     
	 * @created 2018年10月7日 下午5:52:36  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getWhereId_detail", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Tab_apply_shops> getWhereId_detail(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<Tab_apply_shops> josn=new JosnModel<Tab_apply_shops>();
		List<String> lable_list=new ArrayList<String>();
		if(!StringUtils.isEmpty(id)){

			//根据商铺id得到信息
			Tab_apply_shops shops=iapply_shopsService.getWhereId(id);
			
			if(!StringUtils.isEmpty(shops)){
				
				//根据商铺id得到商铺照片集合
				List<Tab_images> images_list=iimagesMapper.getWhereLbtAttributeId(shops.getId());
				for (Tab_images tab_images : images_list) {
					//验证图片路径
					if(tab_images.getImgUrl().indexOf("http") ==-1){
    					String imgUrl= SYS_GET.GET_IMG_PATH_URL + tab_images.getImgUrl();
        				tab_images.setImgUrl(imgUrl);
    				}
				}
				shops.setImages_list(images_list);
				
				//根据商铺id得到价格参数
				Tab_price price=ipriceService.getWhwereSetId(shops.getId());
				if(!StringUtils.isEmpty(price)){
					lable_list.add("人均￥"+price.getPer_capita_price());
					lable_list.add(price.getCharging_fee()+"￥起送");
					lable_list.add(price.getDistribution_fee()+"￥配送");
				}
				
				//根据商铺id得到该商铺的推荐商品
				List<Tab_shops_commodity> commodity_list=ishops_commodityService.getWhere_shops_recommend(shops.getId());			
				for (int j = 0; j < commodity_list.size(); j++) {
					Tab_shops_commodity commodity=commodity_list.get(j);
					//验证图片路径
					if(commodity.getImg().indexOf("http") ==-1){
    					String img= SYS_GET.GET_IMG_PATH_URL + commodity.getImg();
    					commodity.setImg(img);
    				}
					//得到该商品的价格参数
					commodity.price=ipriceService.getWhwereSetId(commodity.getId());
					//根据商铺id得到该项目喜欢的人数
	        		int like_num= iproject_like_numService.getProjectLikeNum(commodity.getId());
	        		commodity.like_num=like_num;
	        		//根据商铺id得到分享次数
	        		int share_num=iuser_shareService.getCount(commodity.getId());
	        		commodity.share_num=share_num;
	        		
	        		//数据更新
					commodity_list.set(j, commodity);
				}
				shops.commodity_list=commodity_list;
				
				//根据商铺id得到该项目喜欢的人数
        		int like_num= iproject_like_numService.getProjectLikeNum(shops.getId());
        		shops.like_num=like_num;
        		lable_list.add(like_num+"人喜欢");
        		
        		//根据商铺id得到分享次数
        		int share_num=iuser_shareService.getCount(shops.getId());
        		shops.share_num=share_num;
        		lable_list.add(share_num+"次转发");
        		
        		//根据商铺id得到浏览次数
        		int browse_num=iuser_browseService.getConut(shops.getId());
        		shops.browse_num=browse_num;
        		lable_list.add(browse_num+"次浏览");
        		
        		//根据商铺创建者得到用户信息
        		Tab_user_info  user_info=iuserinfoMapper.getWhereOpenid(shops.getCreator());
        		if(!StringUtils.isEmpty(user_info)){
        			//验证图片路径
					if(user_info.getAvatar().indexOf("http") ==-1){
    					String avatar= SYS_GET.GET_IMG_PATH_URL + user_info.getAvatar();
    					user_info.setAvatar(avatar);
    				}
        			//根据商铺创建者的id以及当前访问用户，得到关注数据
            		Tab_user_follow user_follow=iuser_followService.getWhereUserID(user_info.getId(), openid);
            		user_info.user_follow=user_follow;
        		}
        		shops.user_info=user_info;
        		
        		//根据商铺id得到tabs导航菜单数据
        		List<Tab_tabs> tabs_list=itabsService.getWhere_project_id(shops.getId());
        		for (Tab_tabs tab_tabs : tabs_list) {
        			//根据商铺tabs导航菜单id，查询菜单内容数据
        			List<Tab_tabs_content> list=itabs_contentService.getWhereGetID(tab_tabs.getId());
        			for (Tab_tabs_content tab_tabs_content : list) {
        				//得到图片
        				images_list=iimagesMapper.getWhereLbtAttributeId(tab_tabs_content.getId());
        				for (Tab_images tab_images : images_list) {
        					//验证路径
        					if(tab_images.getImgUrl().indexOf("http") ==-1){
            					String imgUrl= SYS_GET.GET_IMG_PATH_URL + tab_images.getImgUrl();
                				tab_images.setImgUrl(imgUrl);
            				}
        				}
        				tab_tabs_content.images_list=images_list;
        			}
        			tab_tabs.tabs_content_List=list;
				}
        		shops.tabs_list=tabs_list;
        		
        		//根据商铺id得到选购tabs分类菜单
        		shops.shopsChooseType_tabs_list=ishopsChooseType_tabsService.getWhereShopsId(shops.getId());
        		
        		//根据商铺id得到该用户是否喜欢该商铺
        		shops.user_like=iproject_like_numService.getWhereOpenid(shops.getId(), openid);
				

        		//浏览++
        		Tab_user_browse tab_user_browse=new Tab_user_browse();
        		tab_user_browse.setCreator(openid);
        		tab_user_browse.setProject_id(shops.getId());
        		iuser_browseService.save(tab_user_browse);
        		
        		shops.lable_list=lable_list;
			}
			
			josn.state=200;
			josn.msg="获取成功!";
			josn.data=shops;
		}else{
			josn.msg="商铺id不能为空!";
		}
		
		return josn;
	}
	
}

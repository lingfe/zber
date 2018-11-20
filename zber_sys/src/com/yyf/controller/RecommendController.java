package com.yyf.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.yyf.mapper.IimagesMapper;
import com.yyf.mapper.IuserinfoMapper;
import com.yyf.model.JosnModel;
import com.yyf.model.Recommend;
import com.yyf.model.Tab_apply_shops;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_price;
import com.yyf.model.Tab_release_info;
import com.yyf.model.Tab_shops_commodity;
import com.yyf.model.Tab_user_follow;
import com.yyf.model.Tab_user_info;
import com.yyf.service.Iapply_shopsService;
import com.yyf.service.IpriceService;
import com.yyf.service.Irelease_infoService;
import com.yyf.service.Ishops_commodityService;
import com.yyf.service.Iuser_followService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.Iuser_shareService;

/**
 * 
  * 文件名：RecommendController.java
  * 描述：  推荐数据
  * 修改人： lingfe
  * 修改时间：2018年10月11日 下午2:18:32
  * 修改内容：
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private Iapply_shopsService iapply_shopsService;
	
	@Autowired
	private IpriceService ipriceService;
	
	@Autowired
	private Ishops_commodityService ishops_commodityService;
	
	@Autowired 
	private IuserinfoMapper iuserinfoMapper;
	
	@Autowired
	private Irelease_infoService irelease_infoService;
	
	@Autowired 
	private IimagesMapper iimagesMapper;
	
	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	@Autowired
	private Iuser_shareService iuser_shareService;
	
	@Autowired
	private Iuser_followService iuser_followService;
	
	/**
	 * 
	 * 分页得到推荐的信息
	 * @author lingfe     
	 * @created 2018年10月20日 下午1:30:42  
	 * @param pageIndex
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/get", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	JosnModel<Recommend>  get(
			@RequestParam(value="pageIndex",required=false)Integer pageIndex,
			@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<Recommend> josn=new JosnModel<Recommend>();
		Map<String, Object>  map = new HashMap<>();
		List<Object> st=new ArrayList<Object>();
		Recommend recommend=new Recommend();
		//验证非空
		if(!StringUtils.isEmpty(pageIndex)){
			if(!StringUtils.isEmpty(pageNum)){
				map.put("pageIndex", pageIndex);
				map.put("pageNum", pageNum);
				map.put("address", address);
				recommend.pageIndex=pageIndex;
				recommend.pageNum=pageNum;
				
				//得到需要推荐的商铺数据
				map.put("table", "apply_shops");
				List<Tab_apply_shops> shops_list=iapply_shopsService.getRecommend(map);
				if(!StringUtils.isEmpty(shops_list)){
					//根据商铺id得到商铺的其他数据
					this.get_shops_list_details(shops_list,openid);
				}
				
				
				//得到需要推荐的项目
				map.put("table", "release_info");
				List<Tab_release_info> release_info_list =irelease_infoService.getRecommend(map);
				if(!StringUtils.isEmpty(release_info_list)){
					//根据项目id得到项目的其他数据
					this.get_release_info_list_details(release_info_list,openid);
				}
				
				//得到数据
				st.addAll(shops_list);
				st.addAll(release_info_list);
				
				//打乱排序
				Collections.shuffle(st);
				//赋值
				recommend.list=st;
				
				//返回值
				josn.msg="获取成功!";
				josn.state=200;
				josn.data=recommend;
			}else{
				josn.msg="参数错误!";
			}
		}else{
			josn.msg="参数错误!";
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
	
	//处理项目数据，根据id得到其他数据
	List<Tab_release_info> get_release_info_list_details(List<Tab_release_info> list,String openid){
			
			for (int i = 0; i < list.size(); i++) {
				//实例化对象
				Tab_release_info releaseInfo=list.get(i);
				
				//根据项目id得到项目图片数据
        		List<Tab_images> images=iimagesMapper.getWhereLbtAttributeId(releaseInfo.getId());
        		if(!StringUtils.isEmpty(images)){
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
	    			//根据创建者用户与当前访问用户得到是否关注信息
	    			Tab_user_follow user_follow= this.getUserFollow(user_info.getId(), openid);
	    			if(!StringUtils.isEmpty(user_follow)){
	    				user_info.user_follow=user_follow;
	    			}else{
	    				user_info.user_follow=new Tab_user_follow();
	    			}
	    			releaseInfo.user_info=user_info;
	    		}
	    		//重新赋值
	    		list.set(i, releaseInfo);
			}
			
			
			return list;
		}

	//处理商铺数据，根据id得到其他数据
	List<Tab_apply_shops> get_shops_list_details(List<Tab_apply_shops> list,String openid){
		
		for (int i = 0; i < list.size(); i++) {
			Tab_apply_shops shops=list.get(i);
			//根据商铺id得到商铺价格参数
			shops.shops_price = ipriceService.getWhwereSetId(shops.getId());
			
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
    			//根据商铺用户与当前访问用户得到是否关注信息
    			Tab_user_follow user_follow= this.getUserFollow(user_info.getId(), openid);
    			if(!StringUtils.isEmpty(user_follow)){
    				user_info.user_follow=user_follow;
    			}else{
    				user_info.user_follow=new Tab_user_follow();
    			}
    			shops.user_info=user_info;
    		}
    		
    		//重新赋值
    		list.set(i, shops);
		}
		
		return list;
	}

}

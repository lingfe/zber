package com.yyf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_price;
import com.yyf.model.Tab_user_follow;
import com.yyf.service.IpriceService;
import com.yyf.service.Irelease_infoService;
import com.yyf.service.Iuser_followService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.Iuser_shareService;

/**
 * 
  * 文件名：User_followController.java
  * 描述： 关注表数据。表现层
  * 修改人： lingfe
  * 修改时间：2018年10月19日 下午1:03:52
  * 修改内容：
 */
@Controller
@RequestMapping("/user_follow")
public class User_followController {

	@Autowired
	private Iuser_followService iuser_followService;
	
	@Autowired
	private Irelease_infoService irelease_infoService;
	
	@Autowired
	private IpriceService ipriceService;
	
	@Autowired 
	private IuserinfoMapper iuserinfoMapper;
	
	@Autowired 
	private IimagesMapper iimagesMapper;
	
	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	@Autowired
	private Iuser_shareService iuser_shareService;
	
	/**
	 * 
	 * 获取我的关注用户以及被关注用户的最新的项目信息,商铺信息
	 * @author lingfe     
	 * @created 2018年10月20日 下午7:35:09  
	 * @param openid 关注者
	 * @return
	 */
	@RequestMapping(value="/getMyFollow",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody	
	JosnModel<List<Tab_user_follow>> getMyFollow(
			@RequestParam(value="openid",required=false)String openid){
		//实例化对象
		JosnModel<List<Tab_user_follow>> josn=new JosnModel<List<Tab_user_follow>>();
		josn.data=new ArrayList<Tab_user_follow>();
		if(!StringUtils.isEmpty(openid)){			
			//执行查询
			List<Tab_user_follow> list=iuser_followService.getWhereOpenid(openid);
			if(!StringUtils.isEmpty(list)){
				for (Tab_user_follow tab_user_follow : list) {	
					
					//根据被关注用户id得到用户信息
					tab_user_follow.user_info=iuserinfoMapper.getWhereUserID(tab_user_follow.getUser_id());
					if(!StringUtils.isEmpty(tab_user_follow.user_info)){	
						tab_user_follow.user_info.user_follow.setState(tab_user_follow.getState());
						//根据被关注用户openid得到该用户发布项目，1条数据
						tab_user_follow.release_info = irelease_infoService.getWhereOpenId_Top1(tab_user_follow.user_info.getOpenid());
						if(!StringUtils.isEmpty(tab_user_follow.release_info)){
							String id=tab_user_follow.release_info.getId();
							
							//根据项目id得到项目图片数据
			        		List<Tab_images> images=iimagesMapper.getWhereLbtAttributeId(id);
			        		if(!StringUtils.isEmpty(images)){
			        			tab_user_follow.release_info.images=images;
			        		}
			        		
			        		//根据项目id得到该项目喜欢的人数
			        		int like_num= iproject_like_numService.getProjectLikeNum(id);
			        		tab_user_follow.release_info.setLike_num(like_num);
			        		
			        		//根据项目id得到分享次数
			        		int share_num=iuser_shareService.getCount(id);
			        		tab_user_follow.release_info.setShare_num(share_num);
							
							//根据项目id得到价格信息
							Tab_price price= ipriceService.getWhwereSetId(id);
							if(!StringUtils.isEmpty(price)){
								tab_user_follow.release_info.price=price;
				    		}
						}
						
						//根据被关注用户得到商铺信息
					}
					
					josn.data.add(tab_user_follow);
				}
				
				
				josn.msg="获取成功!";
				josn.state=200;
			}else{
				josn.msg="关注用户为空!";
			}
		}else{
			josn.msg="openid不能为空!";
			josn.state=401;
		}
		return josn;
	}
	
	/**
	 * 
	 * 获取或保存或修改
	 * @author lingfe     
	 * @created 2018年10月19日 下午1:35:32  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/get_or_save_or_update",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody	
	JosnModel<Tab_user_follow> save_or_update(Tab_user_follow tab){
		//实例化对象
		JosnModel<Tab_user_follow>  josn=new JosnModel<Tab_user_follow>();
		//验证非空
		if(!StringUtils.isEmpty(tab.getUser_id())){
			if(!StringUtils.isEmpty(tab.getCreator())){
				//得到
				Tab_user_follow user_follow=iuser_followService.getWhereUserID(tab.getUser_id(), tab.getCreator());
				//如果不为空,则执行修改
				if(!StringUtils.isEmpty(user_follow.getId())){
					//修改参数
					user_follow.setVersion(String.valueOf(Integer.parseInt(user_follow.getVersion())+1));
					user_follow.setMdate(tab.getMdate());
					user_follow.setModify(tab.getCreator());
					user_follow.setState(tab.getState());
					
					//执行修改
					int tt=iuser_followService.update(user_follow);
					if(tt>=1){
						josn.msg="修改成功!";
						josn.state=200;
						josn.data=user_follow;
					}else{
						josn.msg="修改失败";
					}
				}else{
					//id为空，保存一条
					//赋值
					tab.setId(UUID.randomUUID().toString().replace("-", ""));
					
					//执行保存
					int tt=iuser_followService.save(tab);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.state=200;
						josn.data=tab;
					}else{
						josn.msg="保存失败";
					}
				}
			}else{
				josn.msg="关注者，openid不能为空!";
			}
		}else{
			josn.msg="被关注者，用户id不能为空!";
		}
		
		return josn;
	}
}

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
import com.yyf.model.Tab_shops_commodity;
import com.yyf.service.IpriceService;
import com.yyf.service.Ishops_commodityService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.Iuser_shareService;

@Controller
@RequestMapping("/shops_commodity")
public class Shops_commodityController {

	@Autowired
	private Ishops_commodityService ishops_commodityService;
	
	@Autowired
	private Iuser_likeService iproject_like_numService;
	
	@Autowired
	private Iuser_shareService iuser_shareService;
	
	@Autowired
	private IpriceService ipriceService;
	
	/**
	 * 
	 * 根据商品id删除商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 下午12:06:00  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteWhereId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<String> deleteWhereId(
			@RequestParam(value="id",required=false)String id){
		//实例化对象
		JosnModel<String> josn=new JosnModel<String>();
		if(!StringUtils.isEmpty(id)){
			try {
				//根据id查询是否还存在
				Tab_shops_commodity tab=ishops_commodityService.getWhereID(id);
				if(!StringUtils.isEmpty(tab)){
					//执行删除
					int tt=ishops_commodityService.deleteWhereId(id);
					if(tt>=1){
						josn.msg="删除成功!";
						josn.state=200;
						josn.data=id;
					}else{
						josn.msg="删除失败!";
					}
				}else{
					josn.msg="已经被删除了!";
				}
			} catch (Exception e) {
				josn.msg=e.getMessage();
				josn.state=500;
				e.printStackTrace();
			}
		}else{
			josn.msg="商品id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 修改商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 上午11:22:32  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shops_commodity>> update(Tab_shops_commodity tab){
		//实例化对象
		JosnModel<List<Tab_shops_commodity>> josn=new JosnModel<List<Tab_shops_commodity>>();
		if(!StringUtils.isEmpty(tab.getId())){
			//根据id查询商品信息
			Tab_shops_commodity commodity=ishops_commodityService.getWhereID(tab.getId());
			if(!StringUtils.isEmpty(commodity)){
				commodity.setMdate(new Date());
				commodity.setModify(tab.getCreator());
				commodity.setCommodityName(tab.getCommodityName());
				commodity.setImg(tab.getImg());
				commodity.setShops_recommend(tab.getShops_recommend());
				commodity.setVersion(String.valueOf(Integer.parseInt(commodity.getVersion())+1));
				
				//执行修改
				int tt=ishops_commodityService.update(commodity);
				if(tt>=1){
					josn.state=200;
					josn.msg="修改成功!";
					//根据shopsChooseType_tabs_id得到商品集合
					josn.data=ishops_commodityService.getWhereShopsChooseType_tabs_id(commodity.getShopsChooseType_tabs_id());
				}else{
					josn.msg="修改失败!";
				}
			}else{
				josn.msg="商品id无效 !被删除了吗?";
			}
			
		}else{
			josn.msg="商品id不能为空!";
		}
		
		return josn;
	}
	
	
	/**
	 * 
	 * 根据商品id查询商品信息
	 * @author lingfe     
	 * @created 2018年10月10日 上午11:01:17  
	 * @param id 商品id
	 * @return
	 */
	@RequestMapping(value="/getWhereID",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Tab_shops_commodity> getWhereID(@RequestParam("id")String id){
		//实例化对象
		JosnModel<Tab_shops_commodity> josn=new JosnModel<Tab_shops_commodity>();
		if(!StringUtils.isEmpty(id)){
			//根据商品id查询商品信息
			josn.data=ishops_commodityService.getWhereID(id);
			josn.state=200;
			josn.msg="获取成功!";
		}else{
			josn.msg="商品id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 根据商铺选购shopsChooseType_tabs_id得到商品集合
	 * @author lingfe     
	 * @created 2018年10月9日 下午5:51:20  
	 * @param shopsChooseType_tabs_id 商品选购tabs分类菜单
	 * @return
	 */
	@RequestMapping(value="/getWhereShopsChooseType_tabs_id",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shops_commodity>> getWhereShopsChooseType_tabs_id(
			@RequestParam(value="shopsChooseType_tabs_id",required=false)String shopsChooseType_tabs_id){
		//实例化对象
		JosnModel<List<Tab_shops_commodity>> josn=new JosnModel<List<Tab_shops_commodity>>();
		if(!StringUtils.isEmpty(shopsChooseType_tabs_id)){
			try {
				//根据shopsChooseType_tabs_id得到商品集合
				List<Tab_shops_commodity> commodity_list=ishops_commodityService.getWhereShopsChooseType_tabs_id(shopsChooseType_tabs_id);			
				for (int j = 0; j < commodity_list.size(); j++) {
					Tab_shops_commodity commodity=commodity_list.get(j);
					//验证图片路径
					if(commodity.getImg().indexOf("http") ==-1){
    					String img= SYS_GET.GET_IMG_PATH_URL + commodity.getImg();
    					commodity.setImg(img);
    				}
					
					//得到该商品的价格参数
					commodity.price=ipriceService.getWhwereSetId(commodity.getId());
					//根据商品id得到该项目喜欢的人数
	        		int like_num= iproject_like_numService.getProjectLikeNum(commodity.getId());
	        		commodity.like_num=like_num;
	        		//根据商品id得到分享次数
	        		int share_num=iuser_shareService.getCount(commodity.getId());
	        		commodity.share_num=share_num;
	        		
	        		//数据更新
					commodity_list.set(j, commodity);
				}
				josn.data=commodity_list;
				josn.msg="获取成功！";
				josn.state=200;
				
			} catch (Exception e) {
				josn.state=500;
				josn.msg=e.getMessage();
				e.printStackTrace();
			}
		}else{
			josn.msg="商品选购tabs分类菜单id不能为空!";
		}
		return josn;
	}
	
	/**
	 * 
	 * 保存一条商品 数据,并返回最新的数据
	 * @author lingfe     
	 * @created 2018年10月7日 下午9:01:07  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<List<Tab_shops_commodity>> save(Tab_shops_commodity tab){
		//实例化对象
		JosnModel<List<Tab_shops_commodity>> josn=new JosnModel<List<Tab_shops_commodity>>();
		if(!StringUtils.isEmpty(tab.getCreator())){
			if(!StringUtils.isEmpty(tab.getShopsChooseType_tabs_id())){
				//赋值
				tab.setId(UUID.randomUUID().toString().replace("-", ""));
				//执行保存
				int tt=ishops_commodityService.save(tab);
				if(tt>=1){
					josn.state = 200;
					josn.msg="保存成功!";
					//根据shopsChooseType_tabs_id得到商品集合
					josn.data=ishops_commodityService.getWhereShopsChooseType_tabs_id(tab.getShopsChooseType_tabs_id());
				}else{
					josn.msg="保存失败!";
				}
			}else{
				josn.msg="商铺选购分类tabs菜单id不能为空!";
			}
		}else{
			josn.state =401;
			josn.msg="openid不能为空!";
		}
		
		return josn;
	}


}

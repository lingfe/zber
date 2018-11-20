package com.yyf.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyf.model.JosnModel;
import com.yyf.model.Tab_price;
import com.yyf.service.IpriceService;

@Controller
@RequestMapping("/price")
public class PriceController {

	@Autowired
	private IpriceService ipriceService;
	
	/**
	 * 
	 * 根据setid也就是归属id得到价格参数
	 * @author lingfe     
	 * @created 2018年10月9日 下午5:42:05  
	 * @param setId
	 * @return
	 */
	@RequestMapping(value="/getWhwereSetId",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Tab_price> getWhwereSetId(@RequestParam(value="setId",required=false)String setId){
		//实例化对象
		JosnModel<Tab_price>  josn=new JosnModel<Tab_price>();
		if(!StringUtils.isEmpty(setId)){
			try {
				//根据setid也就是归属id得到价格参数
				josn.data=ipriceService.getWhwereSetId(setId);
				josn.state=200;
				josn.msg="获取成功!";
			} catch (Exception e) {
				josn.state=500;
				josn.msg=e.getMessage();
				e.printStackTrace();
			}
		}else{
			josn.msg="归属id不能为空!";
		}
		
		return josn;
	}
	
	/**
	 * 
	 * 保存或修改一条价格参数
	 * @author lingfe     
	 * @created 2018年10月9日 下午5:31:50  
	 * @param tab
	 * @return
	 */
	@RequestMapping(value="/save_or_update",method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody 
	JosnModel<Tab_price> save(Tab_price tab){
		//实例化对象
		JosnModel<Tab_price> josn=new JosnModel<Tab_price>();
		//验证id是否为空
		if(StringUtils.isEmpty(tab.getId())){
			//id=null,执行保存
			if(!StringUtils.isEmpty(tab.getSetId())){
				if(!StringUtils.isEmpty(tab.getCreator())){
					//赋值，生成id
					tab.setId(UUID.randomUUID().toString().replace("-", ""));
					//保存
					int tt=ipriceService.save(tab);
					if(tt>=1){
						josn.msg="保存成功!";
						josn.state=200;
						josn.data=tab;
					}else{
						josn.msg="修改失败!";
					}
				}else{
					josn.state=401;
					josn.msg="创建人不能为空!";
				}
				
			}else{
				josn.msg="setId不能未空!setid表示归属";
			}
		}else{
			//id不为空!执行修改
			//根据id查询价格信息
			Tab_price tab_price=ipriceService.getWhereID(tab.getId());
			if(!StringUtils.isEmpty(tab_price)){
				tab_price.setMdate(new Date());
				tab_price.setModify(tab.getCreator());
				tab_price.setCharging_fee(tab.getCharging_fee());
				tab_price.setDistribution_fee(tab.getDistribution_fee());
				tab_price.setPer_capita_price(tab.getPer_capita_price());
				
				tab_price.setPresent_price(tab.getPresent_price());
				tab_price.setPrice_company(tab.getPrice_company());
				tab_price.setOriginal_price(tab.getOriginal_price());
				tab_price.setSurplusNum(tab.getSurplusNum());
				tab_price.setSurplusNum_company(tab_price.getSurplusNum_company());
				
				//执行修改
				int tt= ipriceService.update(tab_price);
				if(tt>=1){
					josn.msg="修改成功!";
					josn.state=200;
					josn.data=tab_price;
				}else{
					josn.msg="修改失败!";
				}
			}else{
				josn.msg="修改失败!id无效,被删除了吗?";
			}
		}
		
		return josn;
	}
}

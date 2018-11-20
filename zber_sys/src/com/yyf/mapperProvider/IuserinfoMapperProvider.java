package com.yyf.mapperProvider;

import java.util.Date;
import java.util.Map;

public class IuserinfoMapperProvider {

	//修改用户信息，细到可以修改每一个字段
	public String update_info(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		sb.append("update user_info set ");
		
		//修改状态
		if(map.get("state")!=null){
			sb.append(" state=").append(map.get("state")).append(",");
		}
		
		//是否认证
		//是否是商铺
		if(map.get("is_merchant")!=null){
			sb.append(" is_merchant=").append(map.get("is_merchant")).append(",");
			//角色
			sb.append(" relo=").append(map.get("relo")).append(",");
		}
		//是否是养值场
		//是否是种植户
		//头像
		if(map.get("avatar")!=null){
			sb.append(" avatar='").append(map.get("avatar")).append("',");
		}
		
		
		//数据版本
		if(map.get("version")!=null){
			sb.append(" version=").append(map.get("version")).append(",");
		}
		
		//修改人
		if(map.get("openid")!=null){
			sb.append(" modify='").append(map.get("openid")).append("',");
		}
		
		//修改时间
		sb.append(" mdate='").append((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())).append("'");
		
		//条件		
		if(map.get("id")!=null){
			sb.append(" where id='").append(map.get("id")).append("'");
		}else if(map.get("openid") !=null){
			sb.append(" where  openid='").append(map.get("openid")).append("'");
		}else{
			sb.append(" where 1=2");
		}
		
		return sb.toString();
	}
	
}

package com.yyf.mapperProvider;

import java.util.Date;
import java.util.Map;

public class PublicMapperProvider  {

	//修改其他数据
	public String update_other(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		
		//数据表
		if(map.get("table")!=null){
			sb.append(map.get("table"));
		}else{
			throw new IllegalArgumentException("table=参数为空");
		}
		sb.append(" set ");
		
		//修改状态
		if(map.get("state")!=null){
			sb.append(" state=").append(map.get("state")).append(",");
		}
		
		//修改用户表信息，user_info
		if(map.get("table") == "user_info"){
			//是否认证
			//是否是商铺
			if(map.get("is_merchant")!=null){
				sb.append(" is_merchant=").append(map.get("is_merchant")).append(",");
				//角色
				sb.append(" relo=").append(map.get("relo")).append(",");
			}
			//是否是养值场
			//是否是种植户
			
			
		}
		
		//修改时间,默认更新
		if(map.get("version")!=null&&map.get("openid")!=null){
			//修改人
			sb.append(" modify=").append(map.get("openid")).append(",");
			//数据版本
			sb.append(" version=").append(map.get("version")).append(",");
		}
		//修改时间
		sb.append(" mdate='").append((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())).append("'");
		
		//条件		
		if(map.get("user_id")!=null){
			sb.append(" where creator='").append(map.get("user_id")).append("'");
		}else if(map.get("openid") !=null){
			sb.append(" where  creator='").append(map.get("openid")).append("'");
		}else if(map.get("id")!=null){
			sb.append(" where id='").append(map.get("id")).append("'");
		}else{
			sb.append(" where 1=2");
		}
		
		return sb.toString();
	}
	
	
	//查询推荐的项目,推荐的商铺等
	public String getRecommend(Map<String, Object> map){
		StringBuilder whereClause = new StringBuilder();
        whereClause.append("select * from ");
        whereClause.append(map.get("table"));
        whereClause.append(" apply_shops where 1=1  ");
        
        //当前城市地址，位置
        if(map.get("address")!=null){
        	whereClause.append(" and address LIKE '%").append(map.get("address")).append("%' ");
        }
        
		//排序
		if(true) {
			whereClause.append(" order by  mdate desc ");
		}
		
		//条件追加,分页查询
		if(map != null){
			if(map.get("pageIndex")!=null && map.get("pageNum")!=null){
				int pageIndex=Integer.parseInt(map.get("pageIndex").toString());
				int pageNum=Integer.parseInt(map.get("pageNum").toString());
				whereClause.append(" limit ").append((pageIndex-1)*pageNum).append(",").append(pageNum);
			}
		}
		
		return whereClause.toString();
	}


}

package com.yyf.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yyf.controller.util.SYS_GET;
import com.yyf.controller.util.UploadUtils;
import com.yyf.model.JosnModel;
import com.yyf.model.Tab_images;
import com.yyf.model.Tab_user_info;
import com.yyf.service.IimagesService;
import com.yyf.service.Iuser_followService;
import com.yyf.service.Iuser_likeService;
import com.yyf.service.IuserinfoService;
import com.yyf.util.OpenIdUtil;

/**
 * 
  * 文件名：UserinfoController.java
  * 描述： 用户信息数据请求， 表示层
  * 修改人： lingfe
  * 修改时间：2017年12月18日 下午2:18:45
  * 修改内容：
 */
@Controller
@RequestMapping("/userinfo")
public class UserinfoController {
	
	//自动装配
	@Autowired
	private IuserinfoService iuserinfoService;
	
	@Autowired
	private Iuser_followService iuser_followService;
	
	@Autowired
	private Iuser_likeService iuser_likeService;
	
	@Autowired
	private IimagesService iimagesService;
	
	/**
     * 修改头像
     */
    @RequestMapping(value = "/modifyHeadPortrait", method = RequestMethod.POST)
    @ResponseBody
    public JosnModel<Object> imageUpload(MultipartFile file,
    		HttpServletRequest request,
    		@RequestParam(value="openid",required=false)String openid) {
    	//实例化对象
    	JosnModel<Object> josn=new JosnModel<Object>();
    	
    	//验证是否登陆
    	if(!StringUtils.isEmpty(openid)){
        	// 判断上传的文件是否为空
            if (file!=null) {
            	try {
            		Tab_user_info info=iuserinfoService.getWhereOpenid(openid);
					//上传图片
            		String folder="avatar";//文件夹，头像
					Map<String, Object> map=UploadUtils.imageUpload(file, request,folder); 
					if(Integer.parseInt(map.get("state").toString()) == 200){
						//保存图片信息
						Tab_images img=new Tab_images();
						img.setId(UUID.randomUUID().toString().replace("-", ""));
						img.setSetId(openid);
						img.setCustom_name(map.get("custom_name").toString());
						img.setFull_path(map.get("full_path").toString());
						img.setImgUrl(map.get("imgUrl").toString());
						img.setOld_name(map.get("old_name").toString());
						img.setStorage_path(map.get("storage_path").toString());
						img.setSuffix(map.get("suffix").toString());
						
						img.setCreator(openid);
						img.setCdate(new Date());
						
						//保存图片信息
						iimagesService.save(img);
						//修改头像
						map.put("avatar", map.get("imgUrl"));
						map.put("openid", openid);
						int tt=iuserinfoService.update_info(map);
						if(tt>=1){
							josn.data=img;
							josn.state=200;
							josn.msg="修改成功!";
							
							//验证图片路径
							if(info.getAvatar().indexOf("http") ==-1){
								//删除文件,带路径的文件名
								String path=SYS_GET.SET_IMG_PATH_URL+info.getAvatar();
						        File file2 = new File(path);
						        if(file2.delete()){
						            System.out.println("deleted path="+img.getFull_path());
						        }
							}
						}else{
							josn.msg="修改失败!";
						}
					}else{
						josn.msg="头像上传失败，修改失败!";
					}
				} catch (NumberFormatException e) {
					josn.msg=e.getMessage();
					josn.state=500;
				} catch (IllegalStateException e) {
					josn.msg=e.getMessage();
					josn.state=500;
				} catch (IOException e) {
					josn.msg=e.getMessage();
					josn.state=500;
				}
            }else {
                josn.msg="请选择要上传的头像";
            }
    	}else{
    		josn.state=401;
    		josn.msg="openid不能为空!";
    	}
    	
        return josn;
    }
    
	
	/**
	 * 
	 * 根据openid获取用户信息
	 * @author lingfe     
	 * @created 2018年10月2日 上午8:52:39  
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Tab_user_info> getUserInfo(@RequestParam("openid")String openid){
		
		//实例化对象，返回值jsonModel
		JosnModel<Tab_user_info> json= new JosnModel<Tab_user_info>(); 
		
		//验证非空
		if(StringUtils.isEmpty(openid)){
			json.msg="openid不能为空!";
			json.state=401;
			return json;
		}else{
			//根据openid查询用户
			Tab_user_info	user_info = iuserinfoService.getWhereOpenid(openid);
			if(StringUtils.isEmpty(user_info)){
				json.msg ="openid无效，请重新登陆!";
				json.state=401;
				return json;
			}else{
				//根据openid(表示关注者)，查询得到关注人数
				user_info.follow_num=iuser_followService.getWhereOpenidCount(openid);
				//根据openid(创建者),得到喜欢的总数量
				user_info.like_num=iuser_likeService.getWhereOpenidCount(openid);
				json.msg ="获取成功!";
				json.state =200;
				json.data=user_info;
				return json;
			}
		}
		
		
	}
	
	
	/**
	 * 
	 * （微信用户登陆）保存用户信息，并返回info
	 * @author lingfe     
	 * @created 2017年12月18日 下午2:28:22  
	 * @param 用户id
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET})
	@ResponseBody JosnModel<Tab_user_info> save(@RequestParam(value="code",required=false) String code,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="avatar",required=false) String avatar){
		
		//实例化对象
		Tab_user_info tab_user_info=new Tab_user_info();
		JosnModel<Tab_user_info> json= new JosnModel<Tab_user_info>(); 
		//验证非空
		if(StringUtils.isEmpty(code)){
			json.msg="code为空!";
			return json;
		}else if(StringUtils.isEmpty(username)){
			json.msg ="用户信息不能为空!";
			return json;
		}
		
		//验证openid
		String openid=OpenIdUtil.oauth2GetOpenid(code, "2");
		if(StringUtils.isEmpty(openid)){
			json.msg="code无效!请检查";
			return json;
		}else{
			//根据openid查询用户
			Tab_user_info	getWhereOpenid = iuserinfoService.getWhereOpenid(openid);
			if(StringUtils.isEmpty(getWhereOpenid)){
				//设置参数,保存用户
				tab_user_info.setUsername(username);
				tab_user_info.setAvatar(avatar);
				tab_user_info.setOpenid(openid);
				tab_user_info.setCreator(openid);
				
				json.data=tab_user_info;
				
				//执行保存
				int tt=iuserinfoService.save(tab_user_info);
				if(tt >= 1){
					json.state=200;
					json.msg="登陆成功!";
				}else{
					json.msg="登陆失败!系统错误!";
				}
				
				//返回结果
				return json;
			}else{
				json.data=getWhereOpenid;
				json.msg="登陆成功!";
				//返回用户信息
				return json;
			}
		}
	}
}

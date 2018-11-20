package com.yyf.controller;

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

import com.yyf.controller.util.UploadUtils;
import com.yyf.model.JosnModel;
import com.yyf.model.Tab_images;
import com.yyf.service.IimagesService;

@Controller
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	private IimagesService iimagesService;
	
	/**
	 * 
	 * 根据图片id删除图片
	 * @author lingfe     
	 * @created 2018年10月31日 下午7:17:42  
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteWhereId", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JosnModel<Object> del(@RequestParam(value="id",required=false)String id){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(id)){
			//执行删除
			int tt=iimagesService.deleteWhereId(id);
			if(tt>=1){
				josn.state=200;
				josn.msg="删除成功!";
			}else{
				josn.msg="删除失败!";	
			}
		}else{
			josn.msg="删除失败!id为空!";
		}
		
		return josn;
	}
	
	/**
     * 根据文件夹上传图片，单张上传
     * 不开放
     * @param openid
     * @param foler 文件夹名称
     */
    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    @ResponseBody
    public JosnModel<Object> imageUpload(MultipartFile file,
    		HttpServletRequest request,
    		@RequestParam(value="openid",required=false)String openid,
    		@RequestParam(value="setid",required=false)String setid,
    		@RequestParam(value="folder",required=false,defaultValue="default")String folder) {
    	//实例化对象
    	JosnModel<Object> josn=new JosnModel<Object>();
    	
    	//验证是否登陆
    	if(!StringUtils.isEmpty(openid)){
        	// 判断上传的文件是否为空
            if (file!=null) {
            	try {
					//上传图片
					Map<String, Object> map=UploadUtils.imageUpload(file, request,folder); 
					if(Integer.parseInt(map.get("state").toString()) == 200){
						//保存图片信息
						Tab_images img=new Tab_images();
						img.setId(UUID.randomUUID().toString().replace("-", ""));
						img.setSetId(setid);
						img.setCustom_name(map.get("custom_name").toString());
						img.setFull_path(map.get("full_path").toString());
						img.setImgUrl(map.get("imgUrl").toString());
						img.setOld_name(map.get("old_name").toString());
						img.setStorage_path(map.get("storage_path").toString());
						img.setSuffix(map.get("suffix").toString());
						
						img.setCreator(openid);
						img.setCdate(new Date());
						
						//保存图片信息
						int tt=iimagesService.save(img);
						if(tt>=1){
							josn.data=img;
							josn.state=200;
							josn.msg="上传成功!图片保存成功!";
						}else{
							josn.msg="上传成功!图片保存失败!";
						}
					}else{
						josn.msg="上传失败!";
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
                josn.msg="请选择要上传的图片";
            }
    	}else{
    		josn.state=401;
    		josn.msg="openid不能为空!请登陆";
    	}
    	
        return josn;
    }
    
}

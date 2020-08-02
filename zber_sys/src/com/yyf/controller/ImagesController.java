package com.yyf.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import com.yyf.service.IimagesService;

@Controller
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	private IimagesService iimagesService;
	
	/**
	 * 
	 * 根据setId获取图片数据
	 * @author lingfe     
	 * @created 2019年3月25日 下午3:47:07  
	 * @param setId
	 * @return
	 */
	@RequestMapping(value = "/getWhereSetId", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public JosnModel<Object> getWhereSetId(@RequestParam(value="setId",required=false)String setId){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(setId)){
			//执行删除
			List<Tab_images> list=iimagesService.getWhereLbtAttributeId(setId);
			if(list!=null && list.size()>0){
				for (int j = 0; j < list.size(); j++) {
					Tab_images tab_images=list.get(j);
					//验证图片路径
					if(tab_images.getImgUrl().indexOf("http") ==-1){
    					String img= SYS_GET.GET_IMG_PATH_URL + tab_images.getImgUrl();
    					tab_images.setImgUrl(img);
    				}
					
					//数据更新
					list.set(j, tab_images);
				}
			}
			
			josn.state=200;
			josn.msg="查询成功!";
			josn.data=list;
		}else{
			josn.msg="查询失败!id为空!";
		}
		
		return josn;
	}
	
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
    public JosnModel<Object> deleteWhereId(@RequestParam(value="id",required=false)String id){
		//实例化对象
		JosnModel<Object> josn=new JosnModel<Object>();
		if(!StringUtils.isEmpty(id)){
			//执行查询是否存在
			Tab_images img=iimagesService.getWhereId(id);
			if(img!=null){
				//执行删除
				int tt=iimagesService.deleteWhereId(id);
				if(tt>=1){
					
					//验证图片路径
					if(img.getFull_path()!=null&&!"".equals(img.getFull_path())){
						//删除文件,带路径的文件名
				        File file = new File(img.getFull_path());
				        if(file.delete()){
				            System.out.println("deleted path="+img.getFull_path());
				        }
					}
					
			        josn.state=200;
					josn.msg="删除成功!";
				}else{
					josn.msg="删除失败!";	
				}
			}else{
				josn.msg="图片信息不存在!或者id无效!";
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

package com.yyf.controller.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	
	
	/**
	 * 
	 * 图片上传,单张图片
	 * @author lingfe     
	 * @created 2018年10月27日 下午12:07:44  
	 * @param file  图片文件
	 * @param request 请求
	 * @param folder 文件夹名称
	 * @param setId 归属id
	 * @return	josn	上传结果
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> imageUpload(MultipartFile file,
    		HttpServletRequest request,
    		String folder) throws IllegalStateException, IOException{
		//实例化对象
		Map<String, Object> map=new HashedMap();
		
		//定义变量
        String path=null;// 文件路径
        String type=null;// 文件类型
        String fileName=file.getOriginalFilename();// 文件原名称
        map.put("old_name", fileName);
        System.out.println("上传的文件原名称:"+fileName);
        
        // 判断文件类型
        type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
        if (type!=null) {
        	// 判断文件类型是否为空
            if ("GIF".equals(type.toUpperCase())
            		||"PNG".equals(type.toUpperCase())
            		||"JPG".equals(type.toUpperCase())) {
            	//后缀
            	map.put("suffix", type);
            	
                // 项目在容器中实际发布运行的根路径,储存路径
                String realPath=request.getSession().getServletContext().getRealPath("/");
                map.put("storage_path", realPath);
                
                //存放与访问目录
            	String set_get="fileUpload\\images\\";
            	String set_get_to="fileUpload/images/";
                
                // 自定义的文件名称
                String uuid=UUID.randomUUID().toString().replace("-", "");//uuid
                map.put("custom_name", uuid);
                String trueFileName=uuid+"."+type;
                String trueFileName_to=trueFileName;
                if(!StringUtils.isEmpty(folder)){
                	trueFileName=set_get+folder+"\\"+trueFileName;
                	trueFileName_to=set_get_to+folder+"/"+trueFileName_to;
                }else{
                	trueFileName=set_get+"\\"+trueFileName;
                	trueFileName_to=set_get_to+"/"+trueFileName_to;
                }
                
                // 图片访问浏览路径
                map.put("imgUrl", trueFileName_to);
                
                // 设置存放图片文件的路径,图片完整路径，全路径
                path=realPath+trueFileName_to;
                map.put("full_path", path);
                System.out.println("存放图片文件的路径:"+path);
                // 转存文件到指定的路径
                file.transferTo(new File(path));
                System.out.println("文件成功上传到指定目录下");
                
                map.put("state", 200);
                map.put("msg", "上传成功!");
                
                
                
                return map;
            }else {
                System.out.println("不是我们想要的文件类型,请按要求重新上传");
                map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
                return null;
            }
        }else {
            System.out.println("文件类型为空");
            map.put("msg", "文件类型为空");
            return null;
        }
	}
	
	
    /** 
     * 得到真实文件名 
     * @param fileName 
     * @return 
     */  
    public static String subFileName(String fileName){  
        //查找最后一个 \ (文件分隔符)位置  
        int index = fileName.lastIndexOf(File.separator);  
        if(index == -1){  
            //没有分隔符，说明是真实名称  
            return fileName;  
        }else {  
            return fileName.substring(index+1);  
        }  
    }  
  
    /** 
     * 获得随机UUID文件名 
     * @param fileName 
     * @return 
     */  
    public static String generateRandonFileName(String fileName){  
        //首相获得扩展名，然后生成一个UUID码作为名称，然后加上扩展名  
        String ext = fileName.substring(fileName.lastIndexOf("."));  
        return UUID.randomUUID().toString()+ext;  
    }  
  
    /** 
     * 获得hashcode 生成二级目录 
     * @param uuidFileName 
     * @return 
     */  
    public static String generateRandomDir(String uuidFileName){  
        int hashCode = uuidFileName.hashCode();//得到它的hashcode编码  
        //一级目录  
        int d1 = hashCode & 0xf;  
        //二级目录  
        int d2 = (hashCode >> 4) & 0xf;  
        return "/"+d1+"/"+d2;  
    } 
    
    /**
     * 
     * 获取当前的年月日
     * @author lingfe     
     * @created 2017年12月20日 下午1:59:35  
     * @return
     */
    public static String getYiJieDate(){
        Date d = new Date();  
        System.out.println(d);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        String dateNowStr = sdf.format(d);   
        
        return dateNowStr;
    }
}

package com.yyf.controller.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class UploadUtils {
	

	
	/**
	 * 
	 * 文件上传(单个)
	 * @author lingfe     
	 * @created 2019年3月27日 下午3:28:40  
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static String fileUpLoad(CommonsMultipartFile file, 
			HttpServletRequest request) throws Exception {
		// 获取ServletContext的对象 代表当前WEB应用
		ServletContext servletContext = request.getServletContext();
		//将当前日期作为目录
		String dateStr=getYiJieDate();
		// 得到文件上传目的位置的真实路径
		String realPath = servletContext.getRealPath("/fileUpload/"+dateStr);
		System.out.println("realPath :" + realPath);
		File file1 = new File(realPath);
		if (!file1.exists()) {
			// 如果该目录不存在，就创建此抽象路径名指定的目录。
			file1.mkdir();
		}
		String prefix = UUID.randomUUID().toString();
		prefix = prefix.replace("-", "");
		// 使用UUID加前缀命名文件，防止名字重复被覆盖
		String fileName = prefix + "_" + file.getOriginalFilename();

		// 声明输入输出流
		InputStream in = file.getInputStream();

		// 指定输出流的位置;
		OutputStream out = new FileOutputStream(new File(realPath + "\\" + fileName));

		// 这段代码也可以用IOUtils.copy(in, out)工具类的copy方法完成
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
			// 类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
			out.flush();
		}
		// 关闭流
		out.close();
		in.close();
		
		return fileName;
	}

	
	
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
            		||"JPG".equals(type.toUpperCase())
            		||"ICO".equals(type.toUpperCase())) {
            	//后缀
            	map.put("suffix", type);
            	
                // 项目在容器中实际发布运行的根路径,储存路径
                //String realPath=request.getSession().getServletContext().getRealPath("/");
                String realPath=SYS_GET.SET_IMG_PATH_URL;
                map.put("storage_path", realPath);
                
                //存放与访问目录
            	String set_get="images\\";
                
                // 自定义的文件名称
                String uuid=UUID.randomUUID().toString().replace("-", "");//uuid
                map.put("custom_name", uuid);
                String trueFileName=uuid+"."+type;
                //将当前日期作为目录
            	String dateStr=getYiJieDate();
            	
            	//验证文件夹是否为空
                if(!StringUtils.isEmpty(folder)){
                	//路径拼接
                	trueFileName=set_get+folder+"\\"+dateStr+"\\"+trueFileName;
                }else{
                	trueFileName=set_get+dateStr+"\\"+trueFileName;
                }
                
                // 设置存放图片文件的路径,图片完整路径，全路径
                path=realPath+trueFileName;
                //是否创建文件夹
                File file1 = new File(path);
        		if (!file1.exists()) {
        			// 如果该目录不存在，就创建此抽象路径名指定的目录。
        			//file1.mkdir();
        			file1.mkdirs();
        		}
                map.put("full_path", path);
                System.out.println("存放图片文件的路径:"+path);
                // 转存文件到指定的路径
                file.transferTo(new File(path));
                System.out.println("文件成功上传到指定目录下");
                
                // 图片访问浏览路径
                map.put("imgUrl", trueFileName.replace("\\", "/"));
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

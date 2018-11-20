package com.yyf.util;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * JSON 数据格式帮助类
  * 文件名：FastjsonPropertyFilter.java
  * 描述： 
  * 修改人： 杨杰
  * 修改时间：2017年4月7日 下午9:18:47
  * 修改内容：
 */
public class FastjsonPropertyFilter implements PropertyFilter {  
    
    private final Set<String> includes = new HashSet<String>();  
    private final Set<String> excludes = new HashSet<String>();  
      
    public boolean apply(Object source, String name, Object value) {  
        if (excludes.contains(name)) {  
            return false;  
        }  
        if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {  
            return false;  
        }  
        if (includes.size() == 0 || includes.contains(name)) {  
            return true;  
        }  
        if (includes.contains(source.getClass().getSimpleName() + "." + name)) {  
            return true;  
        }  
        return false;  
    }  
      
    public Set<String> getIncludes() {  
        return includes;  
    }  
      
    public Set<String> getExcludes() {  
        return excludes;  
    }  
      
}  
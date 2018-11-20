package com.yyf.model;


public class JosnModel<T> {
	
	//数据
	public T data; 
	
	//msg
	public String msg="系统错误!";
	
	//state
	public int state=0; //=0,没有完成执行,=200成功执行，=401回话过期，请重新登陆
	
	
	
}

package com.yyf.model;

public class FtpConfig {  
      
    @Override
	public String toString() {
		return "FtpConfig [FTP_ADDRESS=" + FTP_ADDRESS + ", FTP_PORT=" + FTP_PORT + ", FTP_USERNAME=" + FTP_USERNAME
				+ ", FTP_PASSWORD=" + FTP_PASSWORD + ", FTP_BASEPATH=" + FTP_BASEPATH + ", IMAGE_BASE_URL="
				+ IMAGE_BASE_URL + "]";
	}

	public String getFTP_ADDRESS() {
		return FTP_ADDRESS;
	}

	public void setFTP_ADDRESS(String fTP_ADDRESS) {
		FTP_ADDRESS = fTP_ADDRESS;
	}

	public String getFTP_PORT() {
		return FTP_PORT;
	}

	public void setFTP_PORT(String fTP_PORT) {
		FTP_PORT = fTP_PORT;
	}

	public String getFTP_USERNAME() {
		return FTP_USERNAME;
	}

	public void setFTP_USERNAME(String fTP_USERNAME) {
		FTP_USERNAME = fTP_USERNAME;
	}

	public String getFTP_PASSWORD() {
		return FTP_PASSWORD;
	}

	public void setFTP_PASSWORD(String fTP_PASSWORD) {
		FTP_PASSWORD = fTP_PASSWORD;
	}

	public String getFTP_BASEPATH() {
		return FTP_BASEPATH;
	}

	public void setFTP_BASEPATH(String fTP_BASEPATH) {
		FTP_BASEPATH = fTP_BASEPATH;
	}

	public String getIMAGE_BASE_URL() {
		return IMAGE_BASE_URL;
	}

	public void setIMAGE_BASE_URL(String iMAGE_BASE_URL) {
		IMAGE_BASE_URL = iMAGE_BASE_URL;
	}

	/** 
     * 获取ip地址   
     */  
    private String FTP_ADDRESS="ftp.daho.club";   
      
    /** 
     * 端口号   
     */   
    private String FTP_PORT="21";   
      
    /** 
     * 用户名   
     */  
    private String FTP_USERNAME="ftp";   
      
    /** 
     * 密码   
     */   
    private String FTP_PASSWORD="ftp";    
      
    /**基本路径   
     */  
    private String FTP_BASEPATH="upload/hjd/shopImg";    
      
    /** 
     * 下载地址地基础url   
     */  
    private String IMAGE_BASE_URL="https\\://static.daho.club/static/upload/";  
    
    /** 
     * 获取图片地址
     */  
    public static String getImgUrl="https://static.daho.club/static/upload/hjd/shopImg/";  
} 

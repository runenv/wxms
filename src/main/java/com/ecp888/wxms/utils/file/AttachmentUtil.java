package com.ecp888.wxms.utils.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ecp888.wxms.utils.AppUtil;

public class AttachmentUtil {

	/**
     * 获取某个文件的前缀路径
     * 不包含文件名的路径
     * @param file   当前文件对象
     * @return
     * @throws IOException
     */
    public static String getFilePrefixPath(File file) throws IOException{
        String path = null;
        if(!file.exists()) {
            throw new IOException("not found the file !" );
        }
        String fileName = file.getName();
        path = file.getPath().replace(fileName, "");
        return path;
    }
    
    /**
     * 获取某个文件的前缀路径
     * 不包含文件名的路径
     * @param path   当前文件路径
     * @return         不包含文件名的路径
     * @throws Exception
     */
    public static String getFilePrefixPath(String path) throws Exception{
        if(null == path || path.isEmpty()) throw new Exception("文件路径为空！");
        int index = path.lastIndexOf(File.separator);
        if(index > 0){
            path = path.substring(0, index + 1);
        }
        return path;
    }
    
    /**
     * 获取不包含后缀的文件路径
     * @param src
     * @return
     */
    public static String getPathWithoutSuffix(String src){
        String path = src;
        int index = path.lastIndexOf(".");
        if(index > 0){
            path = path.substring(0, index + 1);
        }
        return path;
    }
    
    /**
     * 获取文件后缀名
     * @param filePath
     * @return
     */
    public static String getFileSuffix(String filePath){
    	if(!AppUtil.isNotEmpty(filePath))return "";
    	return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    /**
     * 获取文件名
     * @param filePath        文件路径
     * @return                文件名
     * @throws IOException
     */
    public static String getFileName(String filePath){
        if(null == filePath || "".equals(filePath)) return "";
        String[] strArray =  filePath.replaceAll("\\\\", "/").split("/");
        return strArray[strArray.length-1];
    }
    
    /**
     * 移动文件
     * @param pathname
     * @param destFile
     * @return
     */
    public static boolean moveFile(String pathName,File destFile){
    	boolean bool = false;
    	try {
    		if(null ==pathName || "".equals(pathName)) return false;
    		File srcFile = new File(pathName);
    		if(null !=srcFile && srcFile.exists() && !srcFile.isDirectory()){
    			FileUtils.copyFile(srcFile, destFile);
    			srcFile.delete();
    			bool = true;
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return bool;
    }
    
    /**
     * 删除文件
     * @param pathname
     */
    public static void deleteFile(String pathName){
    	if(null ==pathName || "".equals(pathName)) return;
    	File delFile = new File(pathName);
    	if(null !=delFile && delFile.exists()){
    		delFile.delete();
    	}
    }
    
    public static void main(String[] args) {
		String s = "/wxms/images/upload/201408191047.jpg";
		String[] str =  s.split("/");
		System.err.println(str[str.length-1]);
	}
    
	
}

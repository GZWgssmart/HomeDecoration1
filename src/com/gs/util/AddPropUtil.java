package com.gs.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
/**
 * 
 * @author 曾创
 *写入properties文件工具类
 */
public class AddPropUtil {
	
	private static Properties prop;
	
	/**
	 * 
	 * @param key 键
	 * @param value 值
	 * @param filePath 保存路径
	 */
	public AddPropUtil(String key, String value, String filePath) {
		prop = new Properties();
		try {
            OutputStream out = new FileOutputStream(filePath);  
            prop.setProperty(key, value);
            prop.store(out, "key-value");
            out.close();
        } catch (IOException e) {  
            e.printStackTrace();
        }
	}
	
}

package com.gtc.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.gtc.domain.Contact;

public class BeansU {
	/**
	 * 把指定类的所有参数转换成SQL insert语句对应的value后对应的字符串
	 * @param clazz
	 * @return
	 */
	public static  <T> String beanParamsToSQLValueString(Class<T> clazz) {
		List<String> beanParaNames=getBeanParamsNames(clazz);
		StringBuffer sb=new StringBuffer();
		for (String string : beanParaNames) {
			sb.append(":").append(string).append(",");
		}
		String nameStr=sb.toString();
		nameStr=nameStr.substring(0, nameStr.length()-1);
		return nameStr;
	}	
	/**
	 * 把指定类的所有参数(不含id)转换成SQL insert语句对应的value后对应的字符串
	 * 
	 * @param clazz
	 * @return
	 */
	public static  <T> String beanParamsToSQLValueStringWithoutId(Class<T> clazz) {
		List<String> beanParaNames=getBeanParamsNames(clazz);
		StringBuffer sb=new StringBuffer();
		for (String string : beanParaNames) {
			if(!"id".equalsIgnoreCase(string))
				sb.append(":").append(string).append(",");
		}
		String nameStr=sb.toString();
		nameStr=nameStr.substring(0, nameStr.length()-1);
		return nameStr;
	}	
	
	/**
	 * 把指定类的所有参数转换成SQL语句对应的字符串
	 * @param clazz
	 * @return
	 */
	public static  <T> String beanParamsToString(Class<T> clazz) {
		List<String> beanParaNames=getBeanParamsNames(clazz);
		StringBuffer sb=new StringBuffer();
		for (String string : beanParaNames) {
			sb.append(string).append(",");
		}
		String nameStr=sb.toString();
		nameStr=nameStr.substring(0, nameStr.length()-1);
		return nameStr;
	}
	
	/**
	 * 把指定类的所有参数(不包括id)转换成SQL语句对应的字符串
	 * @param clazz
	 * @return
	 */
	public static  <T> String beanParamsToStringWithoutId(Class<T> clazz) {
		List<String> beanParaNames=getBeanParamsNames(clazz);
		StringBuffer sb=new StringBuffer(new String(" "));
		for (String string : beanParaNames) {
			if(!"id".equalsIgnoreCase(string))
				sb.append(string).append(",");
		}
		String nameStr=sb.toString();
		nameStr=nameStr.substring(0, nameStr.length()-1);
		return nameStr;
	}

	/**
	 * 获取指定class的,所有参数名称
	 * @param clazz
	 * @return
	 */
	public static <T> List<String> getBeanParamsNames(Class<T> clazz) {
		List<String> paraNames=new ArrayList<String>();
		try {
			BeanInfo bInfo=Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pds	=bInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				if(!"class".equalsIgnoreCase(pd.getName()))
					paraNames.add(pd.getName());
			
				
			}
			
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block

			throw new RuntimeException(clazz.getName()+"获取参数名称失败");
		}
		return paraNames;
	}
	
	
	public static <T> T getBeanFromRequestParams(Class<T> tarClass,String servletName,HttpServletRequest request){
		
		
		Map<String,String> paraMap=new HashMap();
		Enumeration names=request.getParameterNames();
		if(names==null)
			throw new RuntimeException(servletName+"文件中,传入数据故障");
		//Contact c=new Contact();
		T c;
		try {
			c = tarClass.newInstance();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			throw new RuntimeException(servletName+":创建"+tarClass.getName()+"实例失败");
		} 
		while(names.hasMoreElements()){
			String pName=(String)names.nextElement();
			String pValue=(String)request.getParameterValues(pName)[0];
			if("get".equalsIgnoreCase(request.getMethod())){
				try {
					pValue=new String(pValue.getBytes("iso-8859-1"),"utf-8");//如果是get方法 把默认的GBK编码,转换成UTF-8
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					throw new RuntimeException(servletName+":get方法参数编码转换失败");
				}			
				}

			try {
				BeanUtils.setProperty(c,pName, pValue.trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(servletName+"文件中,传入数据故障");
			} 
		
		}
		
		return c;
		
		
	}
	

}

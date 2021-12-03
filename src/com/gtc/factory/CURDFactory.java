package com.gtc.factory;

import com.gtc.dao.CURD;
import com.gtc.daoimpl.CURDImpl;

public class CURDFactory {
	
	private CURDFactory(){
		
	}
	
	/**
	 * ��ȡ���ݲ�����ʵ�����
	 * @return
	 */
	public static CURD newInstace(){
		CURD curd=new CURDImpl();
		return curd;
		
	}

}

package com.gtc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gtc.dao.CURD;
import com.gtc.domain.Contact;
import com.gtc.factory.CURDFactory;
import com.gtc.util.BeansU;

/**
 * Servlet implementation class UpdateContact
 */
@WebServlet("/UpdateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String html="";
		html+="referfer:"+request.getHeaders("referfer");
		html+="Served at:"+request.getContextPath()+"</br>";
	
		request.getSession().removeAttribute("detail");		//删除原来的数据
		
//		Map<String,String> paraMap=new HashMap();
//		Enumeration names=request.getParameterNames();
//		if(names==null)
//			throw new RuntimeException(this.getServletConfig().getServletName()+"文件中,传入数据故障");
//		Contact c=new Contact();
//		while(names.hasMoreElements()){
//			String pName=(String)names.nextElement();
//			String pValue=(String)request.getParameterValues(pName)[0];
//			if("get".equalsIgnoreCase(request.getMethod()))
//					pValue=new String(pValue.getBytes("iso-8859-1"),"utf-8");			//如果是get方法 把默认的GBK编码,转换成UTF-8
//			
//
//			try {
//				BeanUtils.setProperty(c,pName, pValue.trim());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				throw new RuntimeException(this.getServletConfig().getServletName()+"文件中,传入数据故障");
//			} 
//		
//		}
		Contact c=BeansU.getBeanFromRequestParams(Contact.class, this.getServletConfig().getServletName(), request);
		
		
		html+=c.toString();
		request.getSession().setAttribute("detail", c);		//把新数据写入session
		out.write(html);
		//request.getRequestDispatcher("/jsp/detail.jsp").forward(request, response);
		CURD curd=CURDFactory.newInstace();
		curd.updateUser(c.getId(), c);
		response.sendRedirect(request.getContextPath()+"/jsp/detail.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

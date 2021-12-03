package com.gtc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gtc.domain.Contact;
import com.gtc.factory.CURDFactory;
import com.gtc.util.BeansU;

/**
 * Servlet implementation class AddContact
 */
@WebServlet("/AddContact")
public class AddContact extends HttpServlet {
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
		String referfer= request.getHeader("Referer").trim();
		String path=request.getContextPath().trim();

		if(referfer==null||referfer.isEmpty()||!referfer.contains(path)){
			
			response.sendError(404);								//非法访问

		}
		html+="Served at:"+request.getContextPath()+"</br>";
		Contact c=BeansU.getBeanFromRequestParams(Contact.class, this.getServletConfig().getServletName(), request);
		
		c=CURDFactory.newInstace().register(c);
		request.getSession();
		
		
		HttpSession session=request.getSession();
		List<Contact> contactInfo=(List<Contact>)session.getAttribute("contactInfo");
	
		if(contactInfo==null){
			contactInfo=new ArrayList<Contact>();
		}
		contactInfo.add(c);
		session.setAttribute("contactInfo", contactInfo);		
		
		
		response.sendRedirect(path+"/contact.html");
		//request.getRequestDispatcher("/contact.html").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

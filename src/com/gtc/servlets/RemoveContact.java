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
 * Servlet implementation class RemoveContact
 */
@WebServlet("/RemoveContact")
public class RemoveContact extends HttpServlet {
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

		Contact c=BeansU.getBeanFromRequestParams(Contact.class, this.getServletConfig().getServletName(), request);
		CURDFactory.newInstace().deletUser(c.getId());
		//request.getRequestDispatcher("/contact.html").forward(request, response);;
		response.sendRedirect(request.getContextPath()+"/contact.html");
//		if(CURDFactory.newInstace().deletUser(c.getId())){
//			
//			HttpSession session=request.getSession();				//如果删除成功则需要更新session
//			session.setAttribute("contactInfo", CURDFactory.newInstace().getAllInfo(Contact.class));
//			
//		}
//		
//		request.getRequestDispatcher("/jsp/contact.jsp").forward(request, response);	

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

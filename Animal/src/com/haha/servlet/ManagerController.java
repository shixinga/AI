package com.haha.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.dao.RuleDao;


/**
 * Servlet implementation class UserLoginController
 */
@WebServlet(name = "ManagerController", urlPatterns = { "/ManagerController" })
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation").trim();
		
		//TODO
		//将RULES分解成条件
		
		if("delete".equals(operation)){
			String idstr = request.getParameter("idstr").trim();
			String[] idlist = idstr.split("\\+");
			for(int i=0;i<idlist.length;i++){
				try{
					new RuleDao().deleteById(Integer.parseInt(idlist[i]));
					
				}catch(Exception e){
				}
			}
			response.getWriter().write("success delete");
			
			
		}else if("add".equals(operation)){
			String rules = request.getParameter("conditions");
			String result =  request.getParameter("result");
			String can_end = request.getParameter("can_end");
			System.out.println(rules + "..." + result + "...." + can_end);
			new RuleDao().addRule(rules, result, can_end);
			System.out.println("addhahah");
			response.getWriter().write("success add");
		}
		else{
			System.out.println("none");
		}
		
	}

}

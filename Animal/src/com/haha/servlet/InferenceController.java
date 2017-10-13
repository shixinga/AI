package com.haha.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.service.InferenceService;

/**
 * Servlet implementation class UserLoginController
 */
@WebServlet(name = "InferenceController", urlPatterns = { "/InferenceController" })
public class InferenceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InferenceController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String rules_str = request.getParameter("rules").trim();
		//System.out.println(rules_str+"()");
		//TODO
		//将RULES分解成条件
		ArrayList<String> rules = new ArrayList<String>();
		String[] conditionlist = rules_str.split("\\+");
		for( String condition:conditionlist){
			rules.add(condition);
		}
		
		String result = new InferenceService(rules).MatchAndFindResult();
		if(result!=null)
		//System.out.println(result+"!");
			response.getWriter().write(result);
		else
			response.getWriter().write("0");
		
	}

}

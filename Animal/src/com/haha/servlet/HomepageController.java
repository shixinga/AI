package com.haha.servlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.dao.FeatureDao;
import com.haha.dao.RuleDao;
import com.haha.model.Rule;

/**
 * Servlet implementation class UserLoginController
 */
@WebServlet(name = "HomepageController", urlPatterns = { "/HomepageController" })
public class HomepageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomepageController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<String> features = (ArrayList<String>) new FeatureDao().getFeatures();
		ArrayList<Rule> rules = (ArrayList<Rule>) new RuleDao().getAllRules();
		
		request.setAttribute("rules", rules);
		request.setAttribute("features", features);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}

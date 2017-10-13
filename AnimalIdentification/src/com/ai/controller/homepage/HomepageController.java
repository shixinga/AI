package com.ai.controller.homepage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ai.controller.Inference;
import com.ai.dao.FeatureDAO;
import com.ai.dao.RulesDAO;
import com.ai.vo.RuleVO;

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
		
		ArrayList<String> features = new FeatureDAO().getFeatureList();
		ArrayList<RuleVO> rules = new RulesDAO().getRules();
		
		request.setAttribute("rules", rules);
		request.setAttribute("features", features);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}

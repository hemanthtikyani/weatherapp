package com.skycast.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.skycast.weather.manager.SkycastUserManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Logger logger = Logger.getLogger(LoginController.class);
		
		String username = request.getParameter("username");
		
		SkycastUserManager loginManagerImpl = new SkycastUserManager();
		
		boolean flag = loginManagerImpl.validateUser(username);
		
		if(flag){
			logger.debug("User "+username + " logged in successfully.");
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("userhistory", loginManagerImpl.getUserHistory(username));
			request.getRequestDispatcher("/jsps/weather.jsp").forward(request, response);
		}else{
			request.setAttribute("errorMessage", "Username doesn't exist!");
			request.getRequestDispatcher("/jsps/index.jsp").forward(request, response);
		}
		
		
	}

}

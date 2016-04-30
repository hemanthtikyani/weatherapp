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
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
		
		Logger logger = Logger.getLogger(RegisterController.class);
		
		String username = request.getParameter("username");
		
		SkycastUserManager loginManagerImpl = new SkycastUserManager();
		
		boolean flag = loginManagerImpl.registerUser(username);
		
		if(flag){
			logger.debug("User "+username + " has registered successfully.");
			request.getSession().setAttribute("userhistory", loginManagerImpl.getUserHistory(username));
			request.getSession().setAttribute("username", username);
			request.getRequestDispatcher("/jsps/weather.jsp").forward(request, response);
		}else{
			request.setAttribute("errorMessage", "Username already exists!");
			request.getRequestDispatcher("/jsps/register.jsp").forward(request, response);
		}
	}

}

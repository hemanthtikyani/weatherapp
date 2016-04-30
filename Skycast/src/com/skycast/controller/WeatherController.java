package com.skycast.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.skycast.model.SkycastModel;
import com.skycast.weather.manager.SkycastUserManager;
import com.skycast.weather.manager.SkycastWeatherManager;

/**
 * Servlet implementation class WeatherController
 */
@WebServlet("/WeatherController")
public class WeatherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherController() {
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

		Logger logger = Logger.getLogger(WeatherController.class);
		
		String username = request.getSession().getAttribute("username").toString();
		String address = request.getParameter("address");
		
		logger.debug("User "+username + " has searched for address -  "+address);
		
		SkycastUserManager loginManagerImpl = new SkycastUserManager();
		
		SkycastWeatherManager impl = new SkycastWeatherManager();
		
		SkycastModel model = impl.getLocationDetails(address.replaceAll(" ", "%20"));
		
		if(null!=model){
			if(null!= model.getStatus() && !"error".equals(model.getStatus())){
				loginManagerImpl.addAddress(username, address);
				request.getSession().setAttribute("userhistory", loginManagerImpl.getUserHistory(username));
			}
		}
		
		request.setAttribute("weatherdata", model);
		request.getRequestDispatcher("/jsps/weather.jsp").forward(request, response);
		
		
	}

}

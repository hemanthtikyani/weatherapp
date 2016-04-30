package com.skycast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SkycastLoginFilter
 */
@WebFilter("/jsps/*")
public class SkycastLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SkycastLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession();
        
        String requestPath = httpRequest.getRequestURI();
        
        String contextPath = httpRequest.getContextPath();
        
        if(requestPath.equals(contextPath.concat("/")) || requestPath.equals(contextPath.concat("/jsps/index.jsp")) || requestPath.equals(contextPath.concat("/jsps/register.jsp"))){
        	chain.doFilter(request, response);
        }else{
        	if(null==session.getAttribute("username")){
        		httpResponse.sendRedirect(httpRequest.getContextPath());
        	}else{
        		chain.doFilter(request, response);
        	}
        }
        
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

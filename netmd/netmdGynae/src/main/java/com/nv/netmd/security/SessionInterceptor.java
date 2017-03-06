package com.nv.netmd.security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("inside preHandle"+request.getRequestURI());
		if(request.getSession(true).getAttribute("user") == null ){
			if(request.getRequestURI().equals("/netmd/ws/ui/sync/activateNetMd")){
				return true;
			}
//			if(request.getRequestURI().equals("/netmd/ws/ui/sync/activateNetMd"))
//			{
//				request.getRequestDispatcher("/ws/ui/sync/activateNetMd").forward(request, response);
//				return false;
//			}
			if (request.getRequestURI().equals("/netmd/index") || request.getRequestURI().equals("/netmd/ws/ui/auth/lForm") || request.getRequestURI().equals("/netmd/ws/ui/auth/login")  || request.getRequestURI().equals("/netmd/ws/ui/sync/activateNetMd")) {

				if(request.getRequestURI().equals("/netmd/index")){
					request.getRequestDispatcher("/ws/ui/auth/lForm").forward(request, response);
				}
				return true;
			}	
			response.sendRedirect("/netmd/index");    
			return true;
		}
		return true;
	}


}

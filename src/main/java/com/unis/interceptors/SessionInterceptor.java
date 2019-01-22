package com.unis.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.unis.model.return_model.Result;
import com.unis.utils.Constants;
import com.unis.utils.ERROR_CODE;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if(uri.endsWith(".do")&&!uri.endsWith("login.do")) {
			String session = (String)request.getSession().getAttribute("user");
			if(!Constants.sessionMap.containsKey(session)) {
				PrintWriter write = response.getWriter();
				Result res = new Result();
				res.setCode(ERROR_CODE.LOGIN);
				write.print(Constants.gson.toJson(res));
				write.flush();
				write.close();
				return false;
			}
			
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}

package com.app.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCheckFilter implements Filter {

	private List<String> urlList=null;

	public void init(FilterConfig fConfig) throws ServletException {

		String urls=fConfig.getInitParameter("avoid-urls");
		StringTokenizer st=new StringTokenizer(urls,",");

		urlList=new ArrayList<String>();

		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			urlList.add(s);
		}



	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;

		/*cache clear on logout*/
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		res.setHeader("Pragma", "no-cache");	
		res.setDateHeader("Expires", 0); 
		
		
		
		//read current request URI
		String uri=req.getRequestURI();
		//check with this is exist in List or not?

		boolean flag=urlList.contains(uri);

		try {
			//true means do not check session
			//false means check session and validate
			//if(false==flag)
			if(!flag){
				//read current session
				HttpSession ses=req.getSession(false);
				if(ses==null || ses.getAttribute("userName")==null){
					//if invalid goto home page
					res.sendRedirect(req.getContextPath()+"/mvc/showLogin");
				}

			}


		} catch (Exception e) {
		}

		//if everything is valid continue request
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}

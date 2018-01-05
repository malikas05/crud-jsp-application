package filter;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to check if the user was loged in to the system. If not, the access to the pages except 
 * index.jsp is forbidden. 
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import bean.LoginBean;
import dao.LoginService;

/**
 * Servlet Filter implementation class SecurityFilter
 */

public class SecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
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
		
		boolean skipFilter = true;
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		HttpSession session = httpServletRequest.getSession();
		String path = httpServletRequest.getServletPath();
		String path2 = httpServletRequest.getRequestURI();
		
		if (path2.endsWith(".css")){
			chain.doFilter(request,response);
			return;
		}
		LoginBean user = null;
		
		Cookie cookie = null;
	    Cookie[] cookies = httpServletRequest.getCookies();
	    if (cookies != null) {
		    for (Cookie myCookie : cookies) {
	    			if (myCookie.getName().equals("token"))
	    				cookie = myCookie;
		    }
	    }
	    
		synchronized (session) {
			user = (LoginBean) session.getAttribute("user");
		}
		
		if (path.toLowerCase().equals("/index.jsp") || path.toLowerCase().equals("/loginservlet")
				|| path.toLowerCase().equals("/logout.html")) {
			if (request.getParameter("param") == null) {
				if (user != null) 
					httpServletResponse.sendRedirect("main.jsp");
				else if (cookie != null) {
					String token = cookie.getValue();
					try {
						user = LoginService.loginByToken(token.split(":")[0], token);
						session.setAttribute("user", user);
						httpServletResponse.sendRedirect("main.jsp");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Connection error");
						e.printStackTrace();
					}
				}
				else {
					skipFilter = false;
				}
			}
			else {
				skipFilter = false;
			}
		}
		else {
			if (user == null) {
				httpServletRequest.setAttribute("authentication", "visible");
				request.getRequestDispatcher("index.jsp").forward(request, response);
//				httpServletResponse.sendRedirect("index.jsp");
			}
			else {
				skipFilter = false;
			}
		}
		
		if (!skipFilter)
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

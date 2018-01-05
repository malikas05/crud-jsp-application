package servlets;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This servlet class is used to handle requests from the index.jsp file
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dao.LoginService;
import utilities.HelperClass;
import utilities.TokenGenerator;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("param"); 
		
		if (paramValue != null && paramValue.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
			    for (Cookie myCookie : cookies) {
		    			if (myCookie.getName().equals("token")) {
		    				myCookie.setMaxAge(0);
		    				response.addCookie(myCookie);
		    			}
		    			if (myCookie.getName().equals("user_greeting")) {
		    				myCookie.setMaxAge(0);
		    				response.addCookie(myCookie);
		    			}
			    }
		    }
		    response.sendRedirect("logout.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginBean user = null;
		
		synchronized (session) {
			user = (LoginBean) session.getAttribute("user");
		}
		if (user == null) {
			boolean token = false;
			String username = request.getParameter("loginUsername");
			String password = request.getParameter("loginPassword");
			String rememberMe = request.getParameter("rememeberMe");
			if (rememberMe != null)
				token = true;
			
			LoginBean loginBean = null;
			try {
				if (HelperClass.checkField(username)) {
					loginBean = LoginService.loginUser(username, password, token);
					if (loginBean.getResult().equals("success")) {
						if (rememberMe != null) {
							Cookie cookieToken = new Cookie("token", loginBean.getToken());
							cookieToken.setMaxAge(60 * 60 * 24);
							response.addCookie(cookieToken);
						}
						request.setAttribute("isInvalid", "");
						// cookie for username
						Cookie cookieUsername = new Cookie("user_greeting", username);
						cookieUsername.setMaxAge(60 * 60 * 24);
						response.addCookie(cookieUsername);
						
						session.setAttribute("user", loginBean);
						response.sendRedirect("main.jsp");
					}
					else {
						request.setAttribute("authentication", "inner-show");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
				else {
					request.setAttribute("isInvalid", "is-invalid");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}
	
}

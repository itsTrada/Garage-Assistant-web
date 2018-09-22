package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import database.DatabaseHandler;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fetch the needed info to sign up
		String id = request.getParameter("regId");
		String name = request.getParameter("regName");
		String mobile = request.getParameter("regMobile");
		String email = request.getParameter("regEmail");
		String password = request.getParameter("regPassword");
		String cfrmPassword = request.getParameter("regCfrmPassword");
		
		Member regMember = new Member(id, name, mobile, email , password);
		DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
		Member registered = databaseHandler.handleRegister(regMember);
		
		if (password.toString() != cfrmPassword.toString()) {
			response.sendRedirect("");
			System.out.println("Password is not equal");
		} else if(registered != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", registered);
			request.getRequestDispatcher("/index.jsp").forward(request, response);//load home page
		} else {
			response.sendRedirect("");
			System.out.println("Register error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

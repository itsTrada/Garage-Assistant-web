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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fetch the needed info to log in
		String email = request.getParameter("liEmail");
		String password = request.getParameter("liPassword");
		
		Member liMember = new Member(email, password);
		DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
		
		Member loged = databaseHandler.handleLogin(liMember);
		
		if(loged != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", loged);
			request.getRequestDispatcher("/index.jsp").forward(request, response);//load home page
		}
		else {
			response.sendRedirect("");
			System.out.println("Log in error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

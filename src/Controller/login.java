package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseHandler;
import Model.Member;

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
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		
		Member member = new Member(email, password);
		DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
		
		boolean success = databaseHandler.handleLogin(member);
		
		if(success) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			request.getRequestDispatcher("/home/index.jsp").forward(request, response);//load home page
		}
		else {
			response.sendRedirect("");
			System.out.println("log in error");
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

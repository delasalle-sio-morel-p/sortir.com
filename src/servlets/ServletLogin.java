package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import bll.ParticipantManager;
import bo.Participant;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ParticipantManager participantManager = new ParticipantManager();
	Participant participant;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = null;
		String cookieSelected = null;

		cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loginCookie")) {
					cookieSelected = c.getValue();
				}
			}
		}
		request.setAttribute("login", cookieSelected);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = "Erreur d'identifiant/mot de passe";

		try {

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");
			
			System.out.println(login);
			System.out.println(password);
			System.out.println(remember);

			response.sendRedirect("login?message=" + URLEncoder.encode(message, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui permet de vérifier que le MdP en base de donnée est bien le même
	 * que celui qui est saisi
	 * 
	 * @param password
	 * @param hashedPassword
	 * @return
	 */
	private boolean checkPassword(String password, String hashedPassword) {
		boolean isValid = false;

		if (password == null) {
			throw new IllegalArgumentException("Merci de renseigner un mot de passe correct");
		} else if (password.length() == 0) {
			throw new IllegalArgumentException("Mot de passe trop court");
		}
		isValid = BCrypt.checkpw(password, hashedPassword);
		return isValid;
	}

}

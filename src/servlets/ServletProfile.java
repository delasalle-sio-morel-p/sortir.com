package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ParticipantManager;
import bo.Participant;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServeletProfile
 */
@WebServlet("/profil")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantManager participantManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfile() {
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
		HttpSession httpSession = request.getSession(true);

		Participant participant = (Participant) httpSession.getAttribute("currentSessionParticipant");
		if (participant != null) {
			request.setAttribute("participantEnCours", participant);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Participant participant = new Participant();
		participant = (Participant) request.getAttribute("participantEnCours");

		try {
			this.participantManager.modifier(participant);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

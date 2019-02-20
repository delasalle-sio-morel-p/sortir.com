package servlets;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/ServeletProfile")
public class ServeletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantManager participantManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServeletProfile() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession httpSession = request.getSession();
		int idParticipant = (int)httpSession.getAttribute("currentParticipant");
		Participant participant = new Participant();

		try {
			participant = this.participantManager.selectById(idParticipant);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("participantEnCours", participant);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

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

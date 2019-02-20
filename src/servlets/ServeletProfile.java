package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Participant;
import dal.ParticipantDAO;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServeletProfile
 */
@WebServlet("/ServeletProfile")
public class ServeletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantDAO paticipantDao;

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
		int idParticipant = Integer.parseInt(request.getAttribute("idParticipant").toString());
		Participant participant = new Participant();

		try {
			participant = this.paticipantDao.selectById(idParticipant);
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
			this.paticipantDao.insert(participant);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ParticipantManager;
import bll.SiteManager;
import bo.Participant;
import bo.Site;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServeletProfile
 */
@WebServlet("/profil")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantManager participantManager;
	int error = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfile() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		Participant participant = (Participant) httpSession.getAttribute("currentSessionParticipant");
		request.setAttribute("participantEnCours", participant);

		if (request.getServletPath().equals("/profil")) {
			try {
				if (participant != null) {
					SiteManager siteManager = new SiteManager();
					Site site = siteManager.selectById(participant.getSiteRattachement().getIdSite());
					request.setAttribute("site", site);
					request.setAttribute("profil", participant);

					List<Site> listeSites;
					try {
						listeSites = siteManager.selectAll();
						request.setAttribute("listeSites", listeSites);
					} catch (BusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("login");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageErreurMDP = "Les mots de passe ne sont pas identiques";

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		Participant participantEnCours = (Participant) session.getAttribute("currentSessionParticipant");

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String pseudo = request.getParameter("pseudo");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordVerif = request.getParameter("passwordVerif");
		int idSite = Integer.parseInt(request.getParameter("idSite"));

		ParticipantManager participantManager = new ParticipantManager();

		try {
			Participant participantUpdated = new Participant();
			participantUpdated.setIdparticipant(participantEnCours.getIdparticipant());
			participantUpdated.setNom(nom);
			participantUpdated.setPrenom(prenom);
			participantUpdated.setPseudo(pseudo);
			participantUpdated.setTelephone(telephone);
			participantUpdated.setEmail(email);

			if (!password.isEmpty() && passwordVerif.equals(password)) {
				participantUpdated.setMotDePasse(password);
			} else if (!passwordVerif.equals(password)) {
				error++;
			}

			SiteManager sitemanager = new SiteManager();
			Site site = sitemanager.selectById(idSite);
			participantUpdated.setSiteRattachement(site);

			session.setAttribute("currentSessionParticipant", participantUpdated);

			if (error != 0) {
				request.setAttribute("erreurMDP", messageErreurMDP);
				participantManager.modifierSansMDP(participantUpdated);
				request.setAttribute("participantEnCours", participantUpdated);
				
				error = 0;
				
				doGet(request, response);
			} else {

				participantManager.modifierSansMDP(participantUpdated);

				doGet(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

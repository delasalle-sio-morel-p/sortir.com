package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.InscriptionManager;
import bll.SiteManager;
import bll.SortieManager;
import bo.Inscription;
import bo.Participant;
import bo.Site;
import bo.Sortie;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServletIndex
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccueil() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");

		if (value != null) {
			Participant participantEnCours = (Participant) value;

			request.setAttribute("participantEnCours", participantEnCours);

			SortieManager sortieManager = new SortieManager();
			SiteManager siteManager = new SiteManager();
			InscriptionManager inscriptionManager = new InscriptionManager();
			try {
				List<Sortie> listeSorties = sortieManager.selectAll();
				request.setAttribute("listeSorties", listeSorties);
				List<Site> listeSites = siteManager.selectAll();
				request.setAttribute("listeSites", listeSites);
				List<Inscription> listeInscriptions = inscriptionManager.selectAll();
				request.setAttribute("listeInscriptions", listeInscriptions);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if(request.getServletPath().equals("/search")) {
				
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

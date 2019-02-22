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

import bll.SiteManager;
import bll.SortieManager;
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");

		if (value != null) {
			Participant participantEnCours = (Participant) value;

			request.setAttribute("participantEnCours", participantEnCours);
			
			// Affichage de toutes les sorties existantes en BDD
			SortieManager sortieManager = new SortieManager();
			SiteManager siteManager = new SiteManager();
			try {
				List<Sortie> listeSorties = sortieManager.selectAll();
				request.setAttribute("listeSorties", listeSorties);
				List<Site> listeSites = siteManager.selectAll();
				request.setAttribute("listeSites", listeSites);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

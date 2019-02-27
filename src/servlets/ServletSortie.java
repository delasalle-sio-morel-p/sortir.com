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

import bll.LieuManager;
import bll.SortieManager;
import bll.VilleManager;
import bo.Lieu;
import bo.Participant;
import bo.Sortie;
import bo.Ville;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServletVisualiserRepas
 */
@WebServlet("/details")
public class ServletSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSortie() {
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
			VilleManager villeManager = new VilleManager();
			LieuManager lieuManager = new LieuManager();
			List<Ville> listeVilles;
			List<Lieu> ListeLieux;
			try {
				int idSortie = Integer.parseInt(request.getParameter("idSortie"));
				Sortie sortie = sortieManager.selectAllById(idSortie);
				request.setAttribute("sortie", sortie);
				listeVilles = villeManager.selectAll();
				request.setAttribute("listeVilles", listeVilles);
				ListeLieux = lieuManager.selectAll();
				request.setAttribute("listeLieux", ListeLieux);

			} catch (BusinessException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sortie.jsp");
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
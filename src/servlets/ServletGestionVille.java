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

import exceptions.BusinessException;
import bll.VilleManager;
import bo.Participant;
import bo.Ville;

/**
 * Servlet implementation class ServletNouvelleVille
 */
@WebServlet(urlPatterns = { "/nouvelleVille", "/editerVille", "/supprimerVille" })
public class ServletGestionVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGestionVille() {
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

			if (request.getServletPath().equals("/nouvelleVille")) {
				request.setAttribute("title", "Ajouter");
				request.setAttribute("path", "nouvelleVille");

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formVille.jsp");
				rd.forward(request, response);
			}

			if ((request.getServletPath().equals("/editerVille"))) {
				request.setAttribute("title", "Modifier");
				request.setAttribute("path", "editerVille");

				int idVille = Integer.parseInt(request.getParameter("idVille"));

				try {
					VilleManager villeManager = new VilleManager();
					Ville villeToDisplay = villeManager.selectById(idVille);
					request.setAttribute("ville", villeToDisplay);
				} catch (BusinessException | SQLException e) {
					e.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/formVille.jsp");
				rd.forward(request, response);
			}

			if (request.getServletPath().equals("/supprimerVille")) {
				int idVille = Integer.parseInt(request.getParameter("idVille"));

				try {
					VilleManager villeManager = new VilleManager();
					System.out.println(idVille);
					villeManager.delete(idVille);
				} catch (SQLException e) {
					e.getMessage();
				} catch (BusinessException e) {
					e.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/villes");
				rd.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/nouvelleVille")) {
			request.setCharacterEncoding("UTF-8");
			Ville newVille = new Ville();

			newVille.setNom(request.getParameter("nom"));
			newVille.setCodePostal(request.getParameter("codePostal"));

			VilleManager villeManager = new VilleManager();

			try {
				villeManager.insert(newVille);

			} catch (BusinessException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/villes");
			rd.forward(request, response);
		}

		if (request.getServletPath().equals("/editerVille")) {
			request.setCharacterEncoding("UTF-8");
			Ville villeUpdated = new Ville();

			villeUpdated.setIdVille(Integer.parseInt(request.getParameter("idVille")));
			villeUpdated.setNom(request.getParameter("nom"));
			villeUpdated.setCodePostal(request.getParameter("codePostal"));

			VilleManager villeManager = new VilleManager();

			try {
				villeManager.update(villeUpdated);
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/villes");
			rd.forward(request, response);
		}

		if (request.getServletPath().equals("/supprimerVille")) {

		}

	}
}
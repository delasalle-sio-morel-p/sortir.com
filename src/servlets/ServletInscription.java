package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.InscriptionManager;
import bll.SortieManager;
import bo.Inscription;
import bo.Participant;
import bo.Sortie;
import exceptions.BusinessException;

@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Object currentSessionParticipant = session.getAttribute("currentSessionParticipant");
		
		if (currentSessionParticipant != null) {
			Participant participantEnCours = (Participant) currentSessionParticipant;

			request.setAttribute("participantEnCours", participantEnCours);

			InscriptionManager inscriptionManager = new InscriptionManager();
			SortieManager sortieManager = new SortieManager();
			

			try {
				Inscription inscription = new Inscription();
				Sortie sortie = sortieManager.selectAllById(Integer.parseInt(request.getParameter("idSortie")));
				
				
				Timestamp date = new Timestamp(System.currentTimeMillis());
				
				inscription.setIdSortie(sortie);
				inscription.setIdParticipant(participantEnCours);
				inscription.setDateInscription(date);
				
				inscriptionManager.insert(inscription);
				RequestDispatcher rd = request.getRequestDispatcher("accueil");
				rd.forward(request, response);

			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

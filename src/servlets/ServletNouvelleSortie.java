package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.EtatManager;
import bll.LieuManager;
import bll.SortieManager;
import bll.VilleManager;
import bo.Etat;
import bo.Lieu;
import bo.Participant;
import bo.Sortie;
import bo.Ville;
import exceptions.BusinessException;

/**
 * Servlet implementation class ServletNouvelleSortie
 */
@WebServlet("/nouvelleSortie")
public class ServletNouvelleSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelleSortie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Object value = session.getAttribute("currentSessionParticipant");

		if (value != null) {
			Participant participantEnCours = (Participant) value;

			request.setAttribute("participantEnCours", participantEnCours);
			VilleManager villeManager = new VilleManager();
			LieuManager lieuManager = new LieuManager();
			EtatManager etatManager = new EtatManager();
			List<Ville> listeVilles;
			List<Lieu> ListeLieux;
			List<Etat> ListeEtat;
			try {
				listeVilles = villeManager.selectAll();
				request.setAttribute("listeVilles", listeVilles);
				ListeLieux = lieuManager.selectAll();
				request.setAttribute("listeLieux", ListeLieux);
				ListeEtat = etatManager.selectAll();
				request.setAttribute("listeEtat", ListeEtat);

			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleSortie.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomSortie = request.getParameter("nomSortie");
		String datedebut = request.getParameter("datedebut");
		String datefin = request.getParameter("datefin");
		int ville = 1;//Integer.parseInt(request.getParameter("ville-select"));
		int lieu = 1;//Integer.parseInt(request.getParameter("lieu-select"));
		int nbrePlaces = 12;//Integer.parseInt(request.getParameter("nbrePlaces"));
		String rue = request.getParameter("rue");
		int duree = 60;//Integer.parseInt(request.getParameter("duree"));
		String CP = request.getParameter("CP");
		String description = request.getParameter("description");
		Float latitude = Float.parseFloat(request.getParameter("latitude"));
		Float longitude = Float.parseFloat(request.getParameter("longitude"));
		SortieManager sortieManager = new SortieManager();

		try {
			Sortie sortieInsert = new Sortie();
			if (nomSortie != null) {
				sortieInsert.setNom(nomSortie);
			}
			if (datedebut != null) {
				datedebut = datedebut.replace('T', ' ');
				sortieInsert.setDateHeureDebut(new SimpleDateFormat("yyyy-MM-dd H:m").parse(datedebut));
			}
			if (datefin != null) {
				datefin = datefin.replace('T', ' ');
				sortieInsert.setDateHeureFin(new SimpleDateFormat("yyyy-MM-dd").parse(datefin));
			}
			sortieInsert.setDuree(duree);
			sortieInsert.setNbParticipantMax(nbrePlaces);
			if(description != null) {
				sortieInsert.setDescription(description);
			}
			sortieInsert.setUrlPhoto("rap.jpg");
			if(rue != null && latitude != null && longitude != null) {
				sortieInsert.setLieu(new Lieu(lieu, "", rue, latitude, longitude, new Ville(ville)));
			}
			sortieManager.insert(sortieInsert);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}

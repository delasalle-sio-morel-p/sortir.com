package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import bll.ParticipantManager;
import bll.SiteManager;
import bll.SortieManager;
import bll.VilleManager;
import bo.Etat;
import bo.Lieu;
import bo.Participant;
import bo.Site;
import bo.Sortie;
import bo.Ville;
import exceptions.BusinessException;

@WebServlet("/details")
public class ServletSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletSortie() {
		super();
	}

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
			EtatManager etatManager = new EtatManager();
			ParticipantManager participantManager = new ParticipantManager();
			List<Ville> listeVilles;
			List<Lieu> ListeLieux;
			List<Etat> ListeEtat;
			List<Participant> ListeParticipant;
			try {
				int idSortie = Integer.parseInt(request.getParameter("idSortie"));
				Sortie sortie = sortieManager.selectAllById(idSortie);
				request.setAttribute("sortie", sortie);
				listeVilles = villeManager.selectAll();
				request.setAttribute("listeVilles", listeVilles);
				ListeLieux = lieuManager.selectAll();
				request.setAttribute("listeLieux", ListeLieux);
				ListeEtat = etatManager.selectAll();
				request.setAttribute("listeEtat", ListeEtat);
				ListeParticipant = participantManager.selectAll();
				request.setAttribute("listeParticipant", ListeParticipant);

			} catch (BusinessException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sortie.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nomSortie = request.getParameter("nomSortie");
		String datedebut = request.getParameter("datedebut");
		String datefin = request.getParameter("datefin");
		int ville = Integer.parseInt(request.getParameter("ville-select"));
		int lieu = Integer.parseInt(request.getParameter("lieu-select"));
		int nbrePlaces = Integer.parseInt((request.getParameter("nbrePlaces")));
		String rue = request.getParameter("rue");
		int duree = Integer.parseInt(request.getParameter("duree"));
		String CP = request.getParameter("CP");
		String description = request.getParameter("description");
		Float latitude = Float.parseFloat(request.getParameter("latitude"));
		Float longitude = Float.parseFloat(request.getParameter("longitude"));
		int idSortie = Integer.parseInt(request.getParameter("idSortie"));
		SortieManager sortieManager = new SortieManager();

		try {
			Sortie sortieUpdated = new Sortie();
			if (nomSortie != null) {
				sortieUpdated.setNom(nomSortie);
			}
			if (datedebut != null) {
				datedebut = datedebut.replace('T', ' ');
				sortieUpdated.setDateHeureDebut(new SimpleDateFormat("yyyy-MM-dd H:m").parse(datedebut));
			}
			if (datefin != null) {
				datefin = datefin.replace('T', ' ');
				sortieUpdated.setDateHeureFin(new SimpleDateFormat("yyyy-MM-dd").parse(datefin));
			}
			sortieUpdated.setDuree(duree);
			sortieUpdated.setNbParticipantMax(nbrePlaces);
			if(description != null) {
				sortieUpdated.setDescription(description);
			}
			sortieUpdated.setUrlPhoto("rap.jpg");
			if(rue != null && latitude != null && longitude != null) {
				sortieUpdated.setLieu(new Lieu(lieu, "", rue, latitude, longitude, new Ville(ville)));
			}
			sortieUpdated.setOrganisateur(new Participant());
			sortieUpdated.setIdSortie(idSortie);
			
			sortieManager.update(sortieUpdated);
			doGet(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
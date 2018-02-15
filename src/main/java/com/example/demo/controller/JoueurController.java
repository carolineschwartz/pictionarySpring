package com.example.demo.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.Joueur;
import com.example.demo.service.JoueurService;

@Controller
@RequestMapping("/joueur")
public class JoueurController {

	@Autowired
	private JoueurService joueurService;

	
	// Lancement du formulaire d'incription
	
	@GetMapping("/formulaireInscription")
	public String helloFormulaire() {
		return "pages/formulaire";
	}
	
	
	// Lancement du formulaire de connexion
	
	@GetMapping("/formulaireConnexion")
	public String helloIndex() {
		return "pages/index";
	}
	
	
	// Requete POST : Creation d'un joueur 
	// Verification que les 2 mots de passe soient identiques
	
	@PostMapping("/createJoueur")
	public void createJoueur(@ModelAttribute Joueur joueur) {
		if(joueur.getMdp().equals(joueur.getMdpVerif())) {
			System.out.println(" joueur    :" + joueur.toString());
			joueurService.createJoueur(joueur);
		}
		
	}

	// Requete POST : Connexion d'un joueur 
	// Verification de la cohérence entre le mot de passe de l'email
	@PostMapping("/connexionJoueur")
	public void connexionJoueur(@ModelAttribute Joueur joueur) {
		
		System.out.println("email joueur    :" + joueur.getEmail());
		System.out.println("mdp joueur    :" + joueur.getMdp());
		System.out.println("mdp joueur    :" + joueur.getId());
		
		//List<Joueur> listJoueurs = joueurService.findAllJoueurs();
		Joueur joueurBd = joueurService.findByIdJoueur(joueur.getId());
		
		if(joueurBd!=null) {
			if(joueur.getMdp().equals(joueurBd.getMdp())) {
				System.out.println(" joueur    :" + joueur.toString());
				//return "pages/fenetre";
				System.out.println("CONNEXION OK ");
			} else {	
				System.out.println("ERREUR mot de passe ");
				} 
		}else {
			System.out.println("ERREUR email inexistant ");
		}			
		
	}

	
	/*	
	@RequestMapping(method = RequestMethod.GET)
	public List<Joueur> findAllJoueurs() {
		return joueurService.findAllJoueurs();
	}


*/
}

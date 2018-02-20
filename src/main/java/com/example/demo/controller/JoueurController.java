package com.example.demo.controller;




import java.util.ArrayList;
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
import com.example.demo.modele.Partie;
import com.example.demo.service.JoueurService;
import com.example.demo.service.PartieService;

@Controller
@RequestMapping("/joueur")
public class JoueurController {

	@Autowired
	private JoueurService joueurService;
	
	
	// Lancement du formulaire d'incription
	
	@GetMapping("/formulairefenetreJoueur2")
	public String helloFormulaireJoueur2() {
		System.out.println("formulairefenetreJoueur2");
		return "pages/fenetreJoueur2";
	}
	
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
	
	// Lancement du formulaire Joueur 2
	
	@GetMapping("/formulaireJoueur2")
	public String helloJoueur() {
		return "pages/fenetreJoueur2";
	}
	
	
	@GetMapping("/fenetre")
	public String helloFenetre() {
		return "pages/fenetre";
	}
	
	// Requete POST : Creation d'un joueur 
	// Verification que les 2 mots de passe soient identiques
	
	@PostMapping("/createJoueur")
	public String createJoueur(@ModelAttribute Joueur joueur) {
		
		// Verification email inexistant
		Joueur joueurBd = joueurService.findJoueurByEmail(joueur.getEmail());
		
		if(joueurBd != null) {
			System.out.println("EMAIL EXISTANT ");
			return "pages/formulaire";
		} else {
			System.out.println("EMAIL INEXISTANT ");
			// Verification 2 mots de passe identiques
			if(joueur.getMdp().equals(joueur.getMdpVerif())) {
				System.out.println("MOT de PASSE identiques ");
				joueurService.createJoueur(joueur);
				return "pages/fenetre";
			} else {
				System.out.println("MOT de PASSE differents ");
				return "pages/formulaire";
			}		
			
		}			
		
	}

	// Requete POST : Connexion d'un joueur 
	// Verification de la cohérence entre le mot de passe de l'email
	@PostMapping("/connexionJoueur")
	public String connexionJoueur(@ModelAttribute Joueur joueur) {
		
		Joueur joueurBd = joueurService.findJoueurByEmail(joueur.getEmail());
		
		if(joueurBd != null) {
			if(joueur.getMdp().equals(joueurBd.getMdp())) {	
				System.out.println("CONNEXION OK ");
				return "pages/fenetre";
			} else {	
				System.out.println("ERREUR mot de passe ");				
				return "pages/index";
			}
		}else {
			
		System.out.println("ERREUR email inexistant ");
		return "pages/index";		
		} 				
 }
	
}
		


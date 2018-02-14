package com.example.demo.controller;




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

	@PostMapping("/createJoueur")
	public void createJoueur(@ModelAttribute Joueur joueur) {
		
		System.out.println("Prenom joueur    :" + joueur.getPrenom());
		System.out.println("Nom joueur    :" + joueur.getNom());
		System.out.println("age joueur    :" + joueur.getAge());
		System.out.println("mdp joueur    :" + joueur.getMdp());
		System.out.println("mdpVerif joueur    :" + joueur.getMdpVerif());
		if(joueur.getMdp().equals(joueur.getMdpVerif())) {
			System.out.println(" joueur    :" + joueur.toString());
			joueurService.createJoueur(joueur);
		}
		
	}


	@GetMapping("/formulaireInscription")
	public String helloFormulaire() {
		return "pages/formulaire";
	}
	
	/*	
	@RequestMapping(method = RequestMethod.GET)
	public List<Joueur> findAllJoueurs() {
		return joueurService.findAllJoueurs();
	}


*/
}

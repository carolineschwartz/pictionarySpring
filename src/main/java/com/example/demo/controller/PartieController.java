package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.Joueur;
import com.example.demo.modele.Partie;
import com.example.demo.service.PartieService;
import com.mysql.jdbc.Blob;

@RestController
@RequestMapping("/partie")
public class PartieController {

	@Autowired
	private PartieService partieService;

	
		
	// Requete POST : Creation d'une partie

	@PostMapping("/createPartie")
	public String createPartie(Partie partie) {

		partieService.createPartie(partie);
		return partie.getImageUrl();

	}

	// Mise à jour de la partie (imageUrl et canvasEnvoye = true). Persistance de l'
	// image dans la BDD

	@RequestMapping(path="/sendCanvas", method=RequestMethod.POST, consumes="text/plain; charset=UTF-8" )
	public void sendCanvas(@RequestBody String imageUrl) {

		//System.out.println(" sendCanvas    :" + imageUrl);

		List<Partie> listParties = partieService.findAllPartie();
		int nbPartie = listParties.size();
		Partie lastPartie = listParties.get(nbPartie - 1);

		//System.out.println(" lastPartie    :" + lastPartie);

		lastPartie.setCanvasEnvoye(true);
		lastPartie.setImageUrl(imageUrl);
	//	System.out.println(imageUrl);
		partieService.updatePartie(lastPartie);

		// persister la BBD

	//	System.out.println(" lastPartie modifiee    :" + lastPartie);

	}

	// Requete GET : Image Canvas envoyee ou non

	@RequestMapping(method=RequestMethod.GET, path="/image", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String isImageEnvoyee() {

		String canvasUrl = "";
		// System.out.println(" isImageEnvoyee :");

		List<Partie> listParties = partieService.findAllPartie();
		int nbPartie = listParties.size();

		Partie partieEnCours = listParties.get(nbPartie - 1);
		//System.out.println("partieEnCours");
		//System.out.println(partieEnCours);
		if (listParties.get(nbPartie - 1).getCanvasEnvoye()) {
			canvasUrl = listParties.get(nbPartie - 1).getImageUrl();
			// System.out.println(" isImageEnvoyee :");
		}

		return canvasUrl;

	}
	
	// Mise à jour de la partie (motDevine). Persistance de l'
	// image dans la BDD

	@RequestMapping(path="/sendMotDevine", method=RequestMethod.POST, consumes="text/plain; charset=UTF-8" )
	public void sendMot(@RequestBody String motDevine) {

		System.out.println(" sendMot    :" + motDevine);

		List<Partie> listParties = partieService.findAllPartie();
		int nbPartie = listParties.size();
		Partie lastPartie = listParties.get(nbPartie - 1);

		System.out.println(" lastPartie    :" + lastPartie);

		lastPartie.setMotDevine(motDevine);
		partieService.updatePartie(lastPartie);
		
	}
	
	
	// Requete GET : Envoie du mot devine au dessinateur

	@RequestMapping(method=RequestMethod.GET, path="/getWord")
	public String funcGetWord() {

		String motDevine = "";

		System.out.println("funcGetWord");
		
		List<Partie> listParties = partieService.findAllPartie();
		int nbPartie = listParties.size();
		Partie partieEnCours = listParties.get(nbPartie - 1);
		
		//System.out.println("partieEnCours");
		//System.out.println(partieEnCours);
		if (listParties.get(nbPartie - 1).getMotDevine()!=null) {
			motDevine = listParties.get(nbPartie - 1).getMotDevine();
			System.out.println(" motDevine :"  + motDevine);
		}

		return motDevine;

	}

}

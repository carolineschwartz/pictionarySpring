package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.JoueurDaoCrudRepo;
import com.example.demo.modele.Joueur;

@Service
public class JoueurService {

	    @Autowired
	    private JoueurDaoCrudRepo joueurDao;


	    public List<Joueur> findAllJoueurs(){
	        return (List<Joueur>) this.joueurDao.findAll();
	    }

	    public Joueur findByIdJoueur(int id){
	        return this.joueurDao.findOne((long) id);
	    }


	    public void removeByIdJoueur(int id) {
	        this.joueurDao.delete((long) id);
	    } 


	    public void createJoueur(Joueur joueur) {
	        this.joueurDao.save(joueur);
	    }
	    
	    /*
	         public void updateJoueur(Joueur joueur){
	        this.joueurDao.update(joueur);
	    }
	    
	    */
	     
}




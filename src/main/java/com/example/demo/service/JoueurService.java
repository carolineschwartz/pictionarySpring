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

	    public Joueur findByIdJoueur(long id){
	        return this.joueurDao.findOne(id);
	    }


	    public void removeByIdJoueur(long id) {
	        this.joueurDao.delete(id);
	    } 


	    public void createJoueur(Joueur joueur) {
	        this.joueurDao.save(joueur);
	    }
	    
	    public Joueur findJoueurByEmail(String email) {
	    	return this.joueurDao.findOneByEmail(email);
	    }	    
	     
}




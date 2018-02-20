package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PartieDaoCrudRepo;
import com.example.demo.modele.Partie;

@Service
public class PartieService {

	
	@Autowired
    private PartieDaoCrudRepo partieDao;


    public List<Partie> findAllPartie(){
        return (List<Partie>) this.partieDao.findAll();
    }

    public Partie findByIdPartie(int id){
        return this.partieDao.findOne((long) id);
    }


    public void removeByIdPartie(int id) {
        this.partieDao.delete((long) id);
    } 


    public void createPartie(Partie partie) {
        this.partieDao.save(partie);
        
    }
    
    
   public void updatePartie(Partie partie) {
        this.partieDao.save(partie);
        
    }
}

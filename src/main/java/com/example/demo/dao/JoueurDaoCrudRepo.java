package com.example.demo.dao;


import org.springframework.data.repository.CrudRepository;
import com.example.demo.modele.Joueur;


public interface JoueurDaoCrudRepo extends CrudRepository<Joueur, Long>{

	//void update(Joueur joueur);

}


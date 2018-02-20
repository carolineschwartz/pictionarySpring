package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.modele.Joueur;
import com.example.demo.modele.Partie;

public interface PartieDaoCrudRepo extends CrudRepository<Partie, Long> { 
	
	

}

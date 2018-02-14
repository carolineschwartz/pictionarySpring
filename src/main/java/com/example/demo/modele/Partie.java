package com.example.demo.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Partie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nom;
	private int score;
	private ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(2);
	
	@ManyToMany(mappedBy = "parties")
	private List<Joueur> joueurs;

	
	// Constructeurs
	
	public Partie() {
		
	}
	
	public Partie(int score, ArrayList<Joueur> listeJoueurs) {
		this.nom = "partie_"+id;
		this.score = score;
		this.listeJoueurs = listeJoueurs;
	}

	
	// Getters et Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
	@Override
	public String toString() {
		return "Partie [id=" + id + ", nom=" + nom + ", score=" + score + ", listeJoueurs=" + listeJoueurs + "]";
	}
	
}

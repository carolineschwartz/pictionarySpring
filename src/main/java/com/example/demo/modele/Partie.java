package com.example.demo.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.mysql.jdbc.Blob;

@Entity
public class Partie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nom;
	private int score;
	private ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(2);
	
	@Column(columnDefinition = "TEXT")
	private String imageUrl;
	private String motDevine; // mot deviné par le joueur2
	private boolean canvasEnvoye;
	
	@ManyToMany(mappedBy = "parties")
	private List<Joueur> joueurs;

	
	// Constructeurs
	
	public Partie() {
		
	}
	
	public Partie( ArrayList<Joueur> listeJoueurs,String imageUrl, String mot) {
		this.nom = "partie_" + id;
		this.score = 0;
		this.listeJoueurs = listeJoueurs;
		this.imageUrl = imageUrl;
		this.motDevine = mot;
		this.canvasEnvoye = false;
	}
	
	public Partie(int score, String imageUrl) {
		this.nom = "partie_"+id;
		this.score = score;
		this.canvasEnvoye = false;
	}

	
	// Getters et Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMotDevine() {
		return motDevine;
	}

	public void setMotDevine(String mot) {
		this.motDevine = mot;
	}

	public boolean getCanvasEnvoye() {
		return canvasEnvoye;
	}

	public void setCanvasEnvoye(boolean canvasEnvoye) {
		this.canvasEnvoye = canvasEnvoye;
	}

	@Override
	public String toString() {
		return "Partie [id=" + id + "nom=" + nom + ", score=" + score + ", motDevine=" + motDevine + ", canvasEnvoye=" + canvasEnvoye
				 + "]";
	}

	

	
	
}

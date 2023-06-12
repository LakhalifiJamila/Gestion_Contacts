package com.ensah.core.bo;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;




@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "This field is required")
	private String nom;
	
	@NotBlank(message = "This field is required")
	private String prenom;
	
	@Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	private String telephone1;// (numéro personnel)
	
	@Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	private String telephone2;// (numéro professionnel)
	
	@NotBlank(message = "This field is required")
	private String adresse;
	
	@Email(message = "Enter a valid email")
	@NotBlank(message = "This field is required")
	private String emailPersonnel;
	
	@Email(message = "Enter a valid email")
	@NotBlank(message = "This field is required")
	private String emailProfessionnel;
	
	@NotBlank(message = "This field is required")
	private String genre;// (male or female)

	@ManyToMany(mappedBy = "contacts")
	private Set<Groupe> groupes = new HashSet<Groupe>();
	
	public Contact() {
		
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmailPersonnel() {
		return emailPersonnel;
	}

	public void setEmailPersonnel(String emailPersonnel) {
		this.emailPersonnel = emailPersonnel;
	}

	public String getEmailProfessionnel() {
		return emailProfessionnel;
	}

	public void setEmailProfessionnel(String emailProfessionnel) {
		this.emailProfessionnel = emailProfessionnel;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone1=" + telephone1
				+ ", telephone2=" + telephone2 + ", adresse=" + adresse + ", emailPersonnel=" + emailPersonnel
				+ ", emailProfessionnel=" + emailProfessionnel + ", genre=" + genre + "]";
	}
	
	
}

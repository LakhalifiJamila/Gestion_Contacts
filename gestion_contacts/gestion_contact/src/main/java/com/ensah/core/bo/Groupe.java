package com.ensah.core.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;




@Entity
public class Groupe {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "This field is required")
	private String nom;

	//@NotEmpty(message = "Choose at least one contact")
	//@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, orphanRemoval= false)
	//@JoinColumn(name= "id_group")
	//private List<Contact> contacts= new ArrayList<Contact>();
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "contact_groupe", joinColumns = @JoinColumn(name = "id_groupe"), inverseJoinColumns = @JoinColumn(name = "id_contact"))
	private Set<Contact> contacts = new HashSet<Contact>();

	public void removeContacts() {
		contacts.clear();
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


	public Set<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}


	@Override
	public String toString() {
		return "Groupe [id=" + id + ", nom=" + nom + ", contacts=" + contacts + "]";
	}
	
	
	
}

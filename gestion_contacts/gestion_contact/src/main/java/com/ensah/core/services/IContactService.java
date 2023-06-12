package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Contact;
import com.ensah.core.bo.Groupe;


public interface IContactService {

	public void addContact(Contact pPerson);

	public void updateContact(Contact pPerson);

	public List<Contact> getAllContacts();

	public void deleteContact(Long id);

	public Contact getContactById(Long id);
	
	public List<Contact> findAllASC();
	
	public List<Contact> findAllDESC();
	
	public List<Contact> findByName(String nom);
	
	public List<Contact> findByTelephone(String numero);
	
	//////////////////////////////////
	
	public void addGroupe(Groupe gGroupe);

	public void updateGroupe(Groupe gGroupe);

	public List<Groupe> getAllGroups();

	public void deleteGroup(Long id);

	public Groupe getGroupById(Long id);
	
	public List<Groupe> findGroupByName(String nom);
	
	
}

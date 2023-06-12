package com.ensah.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Contact;
import com.ensah.core.bo.Groupe;
import com.ensah.core.dao.IContactDao;
import com.ensah.core.dao.IGroupeDao;


@Service
@Transactional
//ATT override methods are not treated by the annotation @Transactional
public class ContactServiceImpl implements IContactService{
	
	@Autowired
	private IContactDao contactDao;
	
	@Autowired
	private IGroupeDao groupDao;

	
	public void addContact(Contact cContact) {
		contactDao.save(cContact);
		
	}

	
	public void updateContact(Contact cContact) {
		contactDao.save(cContact);
		
	}

	
	public List<Contact> getAllContacts() {
		
		return contactDao.findAll();
	}

	
	public void deleteContact(Long id) {
		
		List<Groupe> groupes= groupDao.findAll();
		
		for(int i=groupes.size()-1; i>-1; i--) {
			
				
				//delete the contact from the groups where it is
				if(groupes.get(i).getContacts().contains(getContactById(id))) {
					System.out.println("nom du groupe: ========================");
					System.out.println(groupes.get(i));
					System.out.println(groupes.get(i).getContacts());
					System.out.println("=======================================");
					groupes.get(i).getContacts().remove(getContactById(id));
					System.out.println("=====================apres remove()==============");
					System.out.println("nom du groupe: ========================");
					
					
					
				}
			
		}
		
		contactDao.delete(getContactById(id));
		
	}


	
	public Contact getContactById(Long id) {
		
		return contactDao.findById(id).get();
	}


	
	public List<Contact> findAllASC() {
		
		Sort sort = Sort.by(Sort.Direction.ASC, "nom");
		
		return contactDao.findAll(sort);
	}
	
	public List<Contact> findAllDESC() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "nom");
		
		return contactDao.findAll(sort);
	}
	
	
	public List<Contact> findByName(String nom){
		
		/*Sort sort = Sort.by(Sort.Direction.ASC, "nom");
		List<Contact> contacts= contactDao.findAll(sort);
		
		int indice= -1;
		for(int i=0; i<contacts.size(); i++) {
			if(contacts.get(i).getNom().equals(nom)) {
				indice= i;
				break;
			}
		}
		
		List<Contact> searchedContact= new ArrayList<Contact>();
		
		if(indice!=-1) {
			
			searchedContact.add(contacts.get(indice));
		}*/
		
		List<Contact> searchedContact= contactDao.findByName(nom);
		
		return searchedContact;
	}
	
	public List<Contact> findByTelephone(String numero){
	
		
		Sort sort = Sort.by(Sort.Direction.ASC, "nom");
		List<Contact> contacts= contactDao.findAll(sort);
		
		int indice= -1;
		for(int i=0; i<contacts.size(); i++) {
			if(contacts.get(i).getTelephone1().equals(numero) || contacts.get(i).getTelephone2().equals(numero)) {
				indice= i;
				break;
			}
		}
		
		List<Contact> searchedContact= new ArrayList<Contact>();
		
		if(indice!=-1) {
			
			searchedContact.add(contacts.get(indice));
		}
		
		return searchedContact;

	}

///////////////////////////////////////////////////////
	
	public void addGroupe(Groupe gGroupe) {
		
		groupDao.save(gGroupe);
	}


	
	public void updateGroupe(Groupe gGroupe) {
		
		groupDao.save(gGroupe);
	}


	
	public List<Groupe> getAllGroups() {
		
		Sort sort = Sort.by(Sort.Direction.ASC, "nom");
		
		return groupDao.findAll(sort);
	}


	
	public void deleteGroup(Long id) {
		
		//getGroupById(id).removeContacts();
		groupDao.delete(getGroupById(id));
		
		
	}


	
	public Groupe getGroupById(Long id) {
		
		return groupDao.findById(id).get();
	}
	
	
	
	public List<Groupe> findGroupByName(String nom){//le nom des gg
			
			Sort sort = Sort.by(Sort.Direction.ASC, "nom");
			List<Groupe> groups= groupDao.findAll(sort);
			
			int indice= -1;
			for(int i=0; i<groups.size(); i++) {
				if(groups.get(i).getNom().equals(nom)) {
					indice= i;
					break;
				}
			}
			
			List<Groupe> searchedGroup= new ArrayList<Groupe>();
			
			if(indice!=-1) {
				
				searchedGroup.add(groups.get(indice));
			}
			
			return searchedGroup;
		}
	
	
		public List<Groupe> autoGroups(){//appel ds @ModelAttribute////////////////////
			
			
			groupDao.findAll();
			
			
			return null;
		}

}

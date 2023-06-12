package com.ensah.core.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensah.core.bo.Contact;
import com.ensah.core.bo.Groupe;
import com.ensah.core.services.IContactService;


@Controller
public class ContactController {

	protected final Logger LOGGER = Logger.getLogger(getClass());

	
	@Autowired
	private IContactService contactService;
	
	@ModelAttribute
	public void actionParDefaut(Model model) {
		model.addAttribute("action", "/addContact");//l'action par défaut
	}
	
	
	
	@GetMapping("/")
	public String showForm(ModelMap model) {

		model.addAttribute("contactModel", new Contact());
		
		model.addAttribute("listContacts", contactService.findAllASC());

		return "form"; 
	}
	
	@PostMapping("/addContact")
	public String addPerson(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
			ModelMap model) {
		  
		  
		  
		  if (bindingResult.hasErrors()) {//si un ou plusieurs champs ne sont pas valides
			  model.addAttribute("errorMsg", "Les données sont invalides.");
			  LOGGER.warn("Erreur de validation du formulaire");
		  
		  } else {
		  
			  
			  contactService.addContact(contact);
			  model.addAttribute("infoMsg", "Contact ajouté avec succès");
			  model.addAttribute("contactModel", new Contact());
			  
		  }
		  
		  
		  model.addAttribute("listContacts", contactService.findAllASC());
	  
		  
		return "form";

	}
	

	@GetMapping("/supprimer")
	public String supprimer(@RequestParam(name= "id") Long id, Model model) {	//par défaut id est obligatoire

		contactService.deleteContact(id);
		model.addAttribute("infoMsg", "Contact supprimé avec succès.");
		
		model.addAttribute("contactModel", new Contact());//IMPT
			
		model.addAttribute("listContacts", contactService.findAllASC());
		
		return "form";
	}
	
	
	
	
	@PostMapping("/modifier")
	public String modifier(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult, @RequestParam(name= "id") Long id, Model model) {	//par défaut id est obligatoire
		
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("errorMsg", "Les données sont invalides.");
		
		}else {
			contactService.updateContact(contact);
			model.addAttribute("infoMsg", "Contact modifié avec succès.");
			model.addAttribute("contactModel", new Contact());
			
		}
		
		model.addAttribute("listContacts", contactService.findAllASC());
		
		return "form";
	}
	
	
	@GetMapping("/chargerForm")
	public String chargerFormulaire(@RequestParam(name= "id") Long id, ModelMap model) {

		
		model.addAttribute("contactModel", contactService.getContactById(id));
		
		model.addAttribute("action", "/modifier?id="+id);
		
		
		model.addAttribute("listContacts", contactService.findAllASC());
		
		return "form";
	}
	
	@PostMapping("/listOrder")
	public String orderList(@RequestParam("order") int orderValue, ModelMap model) {
		
		if(orderValue==1) {
			
			model.addAttribute("listContacts", contactService.findAllASC());
			
		}else if(orderValue==2){
			
			model.addAttribute("listContacts", contactService.findAllDESC());
		}
		
		
		model.addAttribute("contactModel", new Contact());//IMPT
		  
		return "form";
	}
	
	
	@GetMapping("/searchContact")
	public String searchPage(Model model) {
		
		model.addAttribute("listContacts", contactService.findAllASC());
		
		return "searchPage";
	}
	
	
	@PostMapping("/rechercher")
	public String rechercher(@RequestParam("rechercherContactSelect") String typeRecherche, @RequestParam("search") String searchedValue, Model model) {
		
		
		
		if(typeRecherche.equals("Tous")) {
			
			model.addAttribute("listContacts", contactService.findAllASC());
			
		}else if(typeRecherche.equals("Nom")) {
			
			model.addAttribute("listContacts", contactService.findByName(searchedValue));
			
		}else if(typeRecherche.equals("Numero")) {
			
			model.addAttribute("listContacts", contactService.findByTelephone(searchedValue));
		}
		
		return "searchPage";
	}
	
	@GetMapping("/createGroup")
	public String createGroupePage(ModelMap model) {
		
		
		model.addAttribute("listContacts", contactService.findAllASC());
		
		return "groupRegisterPage";
	}
	
	@PostMapping("/addGroup")
	public String addGroupe(@RequestParam("groupeNom") String groupeNom, @RequestParam(name="contactIds", required= false) List<Long> contactIds, Model model) {
		
		if(contactService.findGroupByName(groupeNom).size()!=0) {
			model.addAttribute("errorMsg", "Un groupe avec le même nom déjà existe.");
			model.addAttribute("listContacts", contactService.findAllASC());
		}else {
			
			Groupe groupe= new Groupe();
			groupe.setNom(groupeNom);
			Set<Contact> contacts= new HashSet<Contact>();
			
			if(contactIds!=null) {
				
				for(int i=0; i<contactIds.size(); i++) {
					contacts.add(contactService.getContactById(contactIds.get(i)));
				}
				
				groupe.setContacts(contacts);
				
				
			}
			
			contactService.addGroupe(groupe);
			
			model.addAttribute("listContacts", contactService.findAllASC());
			
			model.addAttribute("infoMsg", "Groupe ajouté avec succès.");
		}
		
		
		return "groupRegisterPage";
	}
	
	@GetMapping("/Groups")
	public String groupsPage(Model model) {
		
		//appel de la methode auto ici
		
		model.addAttribute("listGroups", contactService.getAllGroups());
		
		return "groupsPage";
	}
	
	@PostMapping("/rechercherGroupe")
	public String rechercherGroupe(@RequestParam("rechercherGroupeSelect") String selectOption, @RequestParam("search") String searchedValue, Model model) {
		
		if(selectOption.equals("Tous")) {
			model.addAttribute("listGroups", contactService.getAllGroups());
		}else if(selectOption.equals("Nom")){
			model.addAttribute("listGroups", contactService.findGroupByName(searchedValue));
		}
		
		return "groupsPage";
	}
	
	
	@GetMapping("/afficherGroupe")
	public String afficherGroupe(@RequestParam("id") Long groupeId, Model model) {
		
		
		model.addAttribute("groupe", contactService.getGroupById(groupeId));
		
		return "afficherGroupe";
	}
	
	@GetMapping("/modifierGroupe")
	public String modifierGroupePage(@RequestParam("id") Long groupeId, Model model) {
		
		
		Groupe groupe= contactService.getGroupById(groupeId);
		
		model.addAttribute("groupe", groupe);
		
		Set<Contact> groupeContacts= groupe.getContacts();
		
		Set<Contact> otherContacts= new HashSet<Contact>();
		
		for(int i=0; i<contactService.getAllContacts().size(); i++) {
			
			if(!(groupeContacts.contains(contactService.getAllContacts().get(i))) ) {
				
				otherContacts.add(contactService.getAllContacts().get(i));
			}
		}
		
		
		model.addAttribute("otherContacts", otherContacts);
		
		
		return "modifierGroupe";
	}
	
	@PostMapping("/modifierGroupe")
	public String modifierGroupe(@RequestParam("groupeId") Long groupeId, @RequestParam("groupName") String groupName, @RequestParam(name="contactIds", required= false) List<Long> contactIds, Model model) {
		
		Groupe groupe= contactService.getGroupById(groupeId);
		
		groupe.setNom(groupName);
		
		groupe.removeContacts();
		
		Set<Contact> contacts= new HashSet<Contact>();
		
		if(contactIds != null) {
			for(int i=0; i<contactIds.size(); i++) {
				
				contacts.add(contactService.getContactById(contactIds.get(i)));
			}
		}
		
		groupe.setContacts(contacts);
		
		contactService.updateGroupe(groupe);
		
		model.addAttribute("groupe", groupe);
		
		model.addAttribute("infoMsg", "Groupe modifié avec succès.");
		
		return "afficherGroupe";
	}
	
	
	@GetMapping("/supprimerGroupe")
	public String supprimerGroupe(@RequestParam("id") Long groupeId, ModelMap model) {
		
		contactService.deleteGroup(groupeId);
		
		model.addAttribute("infoMsg", "Groupe supprimé avec succès.");
		
		model.addAttribute("listGroups", contactService.getAllGroups());
		
		return "groupsPage";
	}
	
}

package com.ensah.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensah.core.bo.Contact;


public interface IContactDao extends JpaRepository<Contact, Long> {

	@Query("SELECT c FROM Contact c WHERE SOUNDEX(c.nom) = SOUNDEX(:nom)")//nom est l'attribut nom de Contact
    List<Contact> findByName(@Param("nom") String nom);
}

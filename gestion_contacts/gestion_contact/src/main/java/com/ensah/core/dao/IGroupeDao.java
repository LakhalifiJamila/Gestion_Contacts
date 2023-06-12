package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Contact;
import com.ensah.core.bo.Groupe;

public interface IGroupeDao extends JpaRepository<Groupe, Long>{//the implementation is given by JpaRepository

}

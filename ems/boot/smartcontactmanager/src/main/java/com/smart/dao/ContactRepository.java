package com.smart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

	@Query("select u from Contact u where u.email=:email")
    Contact getEmployeeByUserName(@Param("email") String email);
	@Query("from Contact as c where c.user.id=:userId")  //cuermt page and contact per page
    Page<Contact> findContactsByUser(@Param("userId")int userId,Pageable pePageable);
	
//	@Query("from Contact as c where c.user.id=:userId")
//	public List<Contact> findContactsByUser(@Param("userId")int userId);
	
	@Modifying
	@Transactional
	@Query(value="delete from Contact c where c.cId =:cId")
    void deleteByIdCustom(@Param("cId")Integer cId);

}

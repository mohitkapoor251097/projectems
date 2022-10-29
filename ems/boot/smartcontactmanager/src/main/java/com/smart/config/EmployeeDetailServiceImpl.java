package com.smart.config;

import com.smart.dao.ContactRepository;
import com.smart.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmployeeDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ContactRepository contactRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Contact contact=contactRepository.getEmployeeByUserName(username);
       if(contact==null)
       {
           throw new UsernameNotFoundException("Could not found Employee....");
       }
       CustomEmployeeDetail customEmployeeDetail=new CustomEmployeeDetail(contact);
       return customEmployeeDetail;
    }
}

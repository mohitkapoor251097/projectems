package com.smart.config;

import com.smart.entities.Contact;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomEmployeeDetail implements UserDetails {

    private final Contact contact;

    public CustomEmployeeDetail(Contact contact) {
        this.contact = contact;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(contact.getRole());
        return List.of(simpleGrantedAuthority);

    }

    @Override
    public String getPassword() {
        return contact.getPassword();
    }

    @Override
    public String getUsername() {
        return contact.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

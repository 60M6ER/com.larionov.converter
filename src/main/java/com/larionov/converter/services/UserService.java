package com.larionov.converter.services;

import com.larionov.converter.entities.User;
import com.larionov.converter.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User userFindByUsername = userRepo.findByUsername(username);


        if(userFindByUsername != null)
        {
            return userFindByUsername;
        }

        return null;
    }
}

package com.example.notes.service;

import com.example.notes.domain.Role;
import com.example.notes.domain.User;
import com.example.notes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            throw new UsernameNotFoundException("User is not found");
        }
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user){
        if(userRepo.findByUsername(user.getUsername())== null) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return true;
        }
        return false;
    }

}

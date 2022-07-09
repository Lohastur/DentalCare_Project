package com.pabloreyes.clinic.configuration.security.service;

import com.pabloreyes.clinic.model.entity.User;
import com.pabloreyes.clinic.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRespository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Not found by username")));

        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        GrantedAuthority autorizacion = null;
//
//        for (Rol rol : user.get().getRoles()) {
//            autorizacion = new SimpleGrantedAuthority(rol.getName());
//            autorizaciones.add(autorizacion);
//        }
        autorizacion = new SimpleGrantedAuthority("ROLE_" + user.get().getRole());

        autorizaciones.add(autorizacion);

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getUsername(),"{noop}" + user.get().getPassword(),true, true, true,true,autorizaciones);
        return userDetail;
    }


}

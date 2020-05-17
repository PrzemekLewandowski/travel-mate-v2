package com.myapp.travelmate.security;

import com.myapp.travelmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Didn't found user: %s.", s)));
    }
}

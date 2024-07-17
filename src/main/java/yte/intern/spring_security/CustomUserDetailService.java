package yte.intern.spring_security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser newUser = new CustomUser(username, username, List.of());
        return newUser;
    }
}

package org.wildcodeschool.MyBlogApplication.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.wildcodeschool.MyBlogApplication.repository.UserRepository;

public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Utilisateur non trouvé avec l'email : %s",email)));
    }
}

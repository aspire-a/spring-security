package yte.intern.spring_security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabasePopulator {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;

    @Autowired
    public DatabasePopulator(AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    Authorities authority1 = new Authorities("ROLE_USER");
    Authorities authority2 = new Authorities("ROLE_ADMIN");


    @PostConstruct
    public void populate(){

        authorityRepository.saveAll(List.of(authority1,authority2));

        CustomUser user1 = new CustomUser("user1","123", List.of(authorityRepository.findByRole("ROLE_USER")));
        CustomUser user2 = new CustomUser("user2","123", List.of(authorityRepository.findByRole("ROLE_USER")));
        CustomUser user3 = new CustomUser("user3","123", List.of(authorityRepository.findByRole("ROLE_USER")));

        CustomUser admin1 = new CustomUser("admin1","123", List.of(authorityRepository.findByRole("ROLE_ADMIN")));
        CustomUser admin2 = new CustomUser("admin2","123", List.of(authorityRepository.findByRole("ROLE_ADMIN")));

        userRepository.saveAll(List.of(user1,user2,user3, admin1, admin2));
    }


}

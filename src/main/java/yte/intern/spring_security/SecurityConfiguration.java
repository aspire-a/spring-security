package yte.intern.spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfiguration {

    @Autowired
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        UserDetails user = User.builder()
                .username("user")
                .password("123")
                .authorities("ROLE_USER").build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("123")
                .authorities("ROLE_ADMIN", "ROLE_USER").build();


        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser(user)
                .withUser(admin)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .requestMatchers(antMatcher("/user")).hasAnyRole("USER", "ADMIN")
                .requestMatchers(antMatcher("/admin")).hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(withDefaults());

        return http.build();
    }
}

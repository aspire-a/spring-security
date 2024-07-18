package yte.intern.spring_security;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authorities, Long> {
    Authorities findByRole(String Role);
}

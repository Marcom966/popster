package com.example.demo.User;

import lombok.extern.flogger.Flogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<UserOfPopster, Long> {
    @Query("select u from UserOfPopster u where u.eMail= ?1")
    Optional<UserOfPopster> findUserByEmail(String email);
}

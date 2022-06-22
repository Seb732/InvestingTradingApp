package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findUserByTeleNumbAndEmail(String teleNumb, String email);

}
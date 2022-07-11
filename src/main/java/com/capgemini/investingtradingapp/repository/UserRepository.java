package com.capgemini.investingtradingapp.repository;

import com.capgemini.investingtradingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User class repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * This method finds the user by first name and last name
     *
     * @param firstName
     * @param lastName
     * @return - user with given first name and last name
     */
    List<User> findUserByFirstNameAndLastName(String firstName, String lastName);

    /**
     * This method finds the user by phone number and email
     *
     * @param teleNumb
     * @param email
     * @return - user wtih given phone number and email
     */
    List<User> findUserByTeleNumbAndEmail(String teleNumb, String email);

}
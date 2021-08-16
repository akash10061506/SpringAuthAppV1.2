package com.connection.oracle.repository.service;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.connection.oracle.entity.Person;

@Repository
public interface PersonSpringDataRepository extends JpaRepository<Person, Integer>
{

	Person findByUserName(String username);

}

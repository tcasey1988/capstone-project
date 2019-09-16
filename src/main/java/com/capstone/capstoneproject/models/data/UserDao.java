package com.capstone.capstoneproject.models.data;

import com.capstone.capstoneproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
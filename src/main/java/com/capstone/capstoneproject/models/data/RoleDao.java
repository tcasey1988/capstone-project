package com.capstone.capstoneproject.models.data;

import com.capstone.capstoneproject.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}

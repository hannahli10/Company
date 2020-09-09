package org.example.repository;
import org.example.model.Role;

import java.util.List;


public interface RoleDao{
    Role save(Role role);
    Role getById(Long id);
    Role getRoleByName(String name);
    boolean delete(Role u);
    List<Role> findAllRoles();
    }


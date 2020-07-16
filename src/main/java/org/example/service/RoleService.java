package org.example.service;

import org.example.model.Role;
import org.example.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role save(Role role){return roleDao.save(role);};
    public Role findById(Integer id){return roleDao.findById(id);};
    public Role getRoleByName(String name){return roleDao.getRoleByName(name);};
    public boolean delete(Role r){return roleDao.delete(r);};

    public List<Role> getAllRoles(){
        List<Role> roleList = roleDao.findAllRoles();
        return roleList;
    };


}
